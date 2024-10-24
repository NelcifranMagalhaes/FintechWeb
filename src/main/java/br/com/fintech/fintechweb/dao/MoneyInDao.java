package br.com.fintech.fintechweb.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.fintechweb.exception.EntityNotFoundException;
import br.com.fintech.fintechweb.factory.ConnectionFactory;
import br.com.fintech.fintechweb.model.MoneyIn;
import br.com.fintech.fintechweb.model.Transaction;


public class MoneyInDao {
    private Connection connection;
    public MoneyInDao() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void add(Transaction moneyIn) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO money_in (id, label, value, created_at, fintech_user_id) VALUES (seq_money_in.nextval, ?, ?, ?, ?)");
        stm.setString(1, moneyIn.getDescription());
        stm.setDouble(2, moneyIn.getValue());
        stm.setString(3, moneyIn.getCreatedAt());
        stm.setInt(4, moneyIn.getFintechUserId());
        stm.executeUpdate();
    }

    private MoneyIn parserMoneyIn(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String label = result.getString("label");
        double value = result.getDouble("value");
        String createdAt = result.getString("created_at");
        int fintechUserId = result.getInt("fintech_user_id");
        return new MoneyIn(id, value, createdAt, label, fintechUserId);
    }

    public MoneyIn search(int id) throws SQLException, EntityNotFoundException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM money_in WHERE id = ?");
        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new EntityNotFoundException("Receita não encontrada");
        return parserMoneyIn(result);
    }

    public List<MoneyIn> getAll() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM money_in");
        ResultSet result = stm.executeQuery();
        List<MoneyIn> listMoneyIn = new ArrayList<>();
        while (result.next()){
            listMoneyIn.add(parserMoneyIn(result));
        }
        return listMoneyIn;
    }
    public void update(MoneyIn moneyIn) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE money_in SET label = ?, value = ?, created_at = ?, fintech_user_id = ? where id = ?");
        stm.setString(1, moneyIn.getDescription());
        stm.setDouble(2, moneyIn.getValue());
        stm.setString(3, moneyIn.getCreatedAt());
        stm.setInt(4, moneyIn.getFintechUserId());
        stm.setLong(5, moneyIn.getId());
        stm.executeUpdate();
    }

    public void remove(int id) throws SQLException, EntityNotFoundException {
        PreparedStatement stm = connection.prepareStatement("DELETE from money_in where id = ?");
        stm.setInt(1, id);
        int line = stm.executeUpdate();
        if (line == 0)
            throw new EntityNotFoundException("Receita não encontrada para ser removida");
    }

}
