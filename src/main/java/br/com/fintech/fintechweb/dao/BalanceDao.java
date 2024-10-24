package br.com.fintech.fintechweb.dao;

import br.com.fintech.fintechweb.exception.EntityNotFoundException;
import br.com.fintech.fintechweb.factory.ConnectionFactory;
import br.com.fintech.fintechweb.model.Balance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BalanceDao {
    private Connection connection;
    public BalanceDao() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void add(Balance balance) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO balance (id, value, created_at, fintech_user_id) VALUES (seq_balance.nextval, ?, ?, ?)");
        stm.setDouble(1, balance.getValue());
        stm.setString(2, balance.getCreatedAt());
        stm.setInt(3, balance.getFintechUserId());
        stm.executeUpdate();
    }

    private Balance parserBalance(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        double value = result.getDouble("value");
        String createdAt = result.getString("created_at");
        int fintechUserId = result.getInt("fintech_user_id");
        return new Balance(id, value, createdAt, fintechUserId);
    }

    public Balance search(int id) throws SQLException, EntityNotFoundException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM balance WHERE id = ?");
        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new EntityNotFoundException("Saldo não encontrado");
        return parserBalance(result);
    }

    public List<Balance> getAll() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM balance");
        ResultSet result = stm.executeQuery();
        List<Balance> listBalance = new ArrayList<>();
        while (result.next()){
            listBalance.add(parserBalance(result));
        }
        return listBalance;
    }

    public void update(Balance balance) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE balance SET value = ?, created_at = ?, fintech_user_id = ? where id = ?");
        stm.setDouble(1, balance.getValue());
        stm.setString(2, balance.getCreatedAt());
        stm.setInt(3, balance.getFintechUserId());
        stm.setLong(4, balance.getId());
        stm.executeUpdate();
    }

    public void remove(int id) throws SQLException, EntityNotFoundException {
        PreparedStatement stm = connection.prepareStatement("DELETE from balance where id = ?");
        stm.setInt(1, id);
        int line = stm.executeUpdate();
        if (line == 0)
            throw new EntityNotFoundException("Saldo não encontrado para ser removido");
    }

}
