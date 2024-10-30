package br.com.fintech.fintechweb.dao;

import br.com.fintech.fintechweb.exception.EntityNotFoundException;
import br.com.fintech.fintechweb.factory.ConnectionFactory;
import br.com.fintech.fintechweb.model.Investment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvestmentDao {
    private Connection connection;
    public InvestmentDao() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void add(Investment investment) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO investment (id, label, value, created_at, fintech_user_id) VALUES (seq_investment.nextval, ?, ?, ?, ?)");
        stm.setString(1, investment.getDescription());
        stm.setDouble(2, investment.getValue());
        stm.setString(3, investment.getCreatedAt());
        stm.setInt(4, investment.getFintechUserId());
        stm.executeUpdate();
        stm.close();
    }

    private Investment parserInvestment(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String label = result.getString("label");
        double value = result.getDouble("value");
        String createdAt = result.getString("created_at");
        int fintechUserId = result.getInt("fintech_user_id");
        return new Investment(id, value, createdAt, label, fintechUserId);
    }

    public Investment search(int id) throws SQLException, EntityNotFoundException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM investment WHERE id = ?");
        stm.setInt(1, id);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new EntityNotFoundException("Investimento não encontrada");
        return parserInvestment(result);
    }

    public List<Investment> getAll(int userId) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM investment WHERE fintech_user_id = ?");
        stm.setInt(1, userId);
        ResultSet result = stm.executeQuery();
        List<Investment> listInvestment = new ArrayList<>();
        while (result.next()){
            listInvestment.add(parserInvestment(result));
        }
        stm.close();
        return listInvestment;
    }

    public void update(Investment investment) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE investment SET label = ?, value = ?, created_at = ? where id = ?");
        stm.setString(1, investment.getDescription());
        stm.setDouble(2, investment.getValue());
        stm.setString(3, investment.getCreatedAt());
        stm.setLong(4, investment.getId());
        stm.executeUpdate();
        stm.close();
    }

    public void remove(int id) throws SQLException, EntityNotFoundException {
        PreparedStatement stm = connection.prepareStatement("DELETE from investment where id = ?");
        stm.setInt(1, id);
        int line = stm.executeUpdate();
        if (line == 0) {
            throw new EntityNotFoundException("Investimento não encontrado para ser removido");
        }
        stm.close();
    }
}
