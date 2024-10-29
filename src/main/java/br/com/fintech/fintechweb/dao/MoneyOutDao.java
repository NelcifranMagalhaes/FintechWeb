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
        PreparedStatement stm = connection.prepareStatement("INSERT INTO money_out (id, label, value, category, created_at, fintech_user_id) VALUES (seq_money_out.nextval, ?, ?,?, ?, ?)");
        stm.setString(1, moneyOut.getDescription());
        stm.setDouble(2, moneyOut.getValue());
        stm.setString(3, moneyOut.getCategory());
        stm.setString(4, moneyOut.getCreatedAt());
        stm.setInt(5, moneyOut.getFintechUserId());
        stm.executeUpdate();
        stm.close();
    }

    private MoneyOut parserMoneyOut(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String label = result.getString("label");
        double value = result.getDouble("value");
        String createdAt = result.getString("created_at");
        int fintechUserId = result.getInt("fintech_user_id");
        String category = result.getString("category");
        return new MoneyOut(id, value, createdAt, label, fintechUserId, category);
    }

    public MoneyOut search(int id) throws SQLException, EntityNotFoundException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM money_out WHERE id = ?");
        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new EntityNotFoundException("Despesa não encontrada");
        return parserMoneyOut(result);
    }

    public List<MoneyOut> getAll(int userId) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM money_out WHERE fintech_user_id = ?");
        stm.setInt(1, userId);
        ResultSet result = stm.executeQuery();
        List<MoneyOut> listMoneyOut = new ArrayList<>();
        while (result.next()){
            listMoneyOut.add(parserMoneyOut(result));
        }
        stm.close();
        return listMoneyOut;
    }
    public void update(MoneyOut moneyOut) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE money_out SET label = ?, category = ?, value = ?, created_at = ? where id = ?");
        stm.setString(1, moneyOut.getDescription());
        stm.setString(2, moneyOut.getCategory());
        stm.setDouble(3, moneyOut.getValue());
        stm.setString(4, moneyOut.getCreatedAt());
        stm.setLong(5, moneyOut.getId());
        stm.executeUpdate();
        stm.close();
    }

    public void remove(int id) throws SQLException, EntityNotFoundException {
        PreparedStatement stm = connection.prepareStatement("DELETE from money_out where id = ?");
        stm.setInt(1, id);
        int line = stm.executeUpdate();
        if (line == 0) {
            throw new EntityNotFoundException("Despesa não encontrada para ser removida");
        }
        stm.close();
    }
}
