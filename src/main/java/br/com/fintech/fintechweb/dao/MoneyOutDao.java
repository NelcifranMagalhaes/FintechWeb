package br.com.fintech.fintechweb.dao;

import br.com.fintech.fintechweb.exception.EntityNotFoundException;
import br.com.fintech.fintechweb.factory.ConnectionFactory;
import br.com.fintech.fintechweb.model.MoneyOut;
import br.com.fintech.fintechweb.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoneyOutDao {
    private Connection connection;
    public MoneyOutDao() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void add(Transaction moneyOut) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO money_out (id, label, value, created_at, fintech_user_id) VALUES (seq_money_out.nextval, ?, ?, ?, ?)");
        stm.setString(1, moneyOut.getDescription());
        stm.setDouble(2, moneyOut.getValue());
        stm.setString(3, moneyOut.getCreatedAt());
        stm.setInt(4, moneyOut.getFintechUserId());
        stm.executeUpdate();
    }

    private MoneyOut parserMoneyOut(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String label = result.getString("label");
        double value = result.getDouble("value");
        String createdAt = result.getString("created_at");
        int fintechUserId = result.getInt("fintech_user_id");
        return new MoneyOut(id, value, createdAt, label, fintechUserId);
    }

    public MoneyOut search(int id) throws SQLException, EntityNotFoundException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM money_out WHERE id = ?");
        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new EntityNotFoundException("Despesa não encontrada");
        return parserMoneyOut(result);
    }

    public List<MoneyOut> getAll() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM money_out");
        ResultSet result = stm.executeQuery();
        List<MoneyOut> listMoneyOut = new ArrayList<>();
        while (result.next()){
            listMoneyOut.add(parserMoneyOut(result));
        }
        return listMoneyOut;
    }
    public void update(MoneyOut moneyOut) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE money_out SET label = ?, value = ?, created_at = ?, fintech_user_id = ? where id = ?");
        stm.setString(1, moneyOut.getDescription());
        stm.setDouble(2, moneyOut.getValue());
        stm.setString(3, moneyOut.getCreatedAt());
        stm.setInt(4, moneyOut.getFintechUserId());
        stm.setLong(5, moneyOut.getId());
        stm.executeUpdate();
    }

    public void remove(int id) throws SQLException, EntityNotFoundException {
        PreparedStatement stm = connection.prepareStatement("DELETE from money_out where id = ?");
        stm.setInt(1, id);
        int line = stm.executeUpdate();
        if (line == 0)
            throw new EntityNotFoundException("Despesa não encontrada para ser removida");
    }
}
