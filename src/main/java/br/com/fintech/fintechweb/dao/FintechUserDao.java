package br.com.fintech.fintechweb.dao;

import br.com.fintech.fintechweb.exception.EntityNotFoundException;
import br.com.fintech.fintechweb.factory.ConnectionFactory;
import br.com.fintech.fintechweb.model.FintechUser;
import br.com.fintech.fintechweb.util.EncryptionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FintechUserDao {
    private Connection connection;

    public void closeConnection() throws SQLException {
        connection.close();
    }
    public FintechUserDao() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }
    public void add(FintechUser fintechUser) throws Exception {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO fintech_user (id, name, email, gender, birth_date, password_hash, created_at) VALUES (seq_fintech_user.nextval, ?, ?, ?, ?, ?, ?)");
        stm.setString(1, fintechUser.getName());
        stm.setString(2, fintechUser.getEmail());
        stm.setString(3, fintechUser.getGender());
        stm.setString(4, fintechUser.getBirthDate());
        stm.setString(5, encryptPassword(fintechUser.getPasswordHash()));
        stm.setString(6, fintechUser.getCreatedAt());
        stm.executeUpdate();
    }

    private String encryptPassword(String password) throws Exception {
        return EncryptionUtils.encrypt(password);
    }

    public FintechUser search(long id) throws SQLException, EntityNotFoundException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM fintech_user WHERE id = ?");
        stm.setLong(1, id);
        ResultSet result = stm.executeQuery();
        if (!result.next())
            throw new EntityNotFoundException("User não encontrado");
        return parserUser(result);
    }

    public List<FintechUser> getAll() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM fintech_user");
        ResultSet result = stm.executeQuery();
        List<FintechUser> listFintechUser = new ArrayList<>();
        while (result.next()){
            listFintechUser.add(parserUser(result));
        }
        return listFintechUser;
    }

    private FintechUser parserUser(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        String email = result.getString("email");
        String gender = result.getString("gender");
        String birthDate = result.getString("birth_date");
        String passwordHash = result.getString("password_hash");
        String createdAt = result.getString("created_at");
        return new FintechUser(id, name, email, gender, birthDate, passwordHash, createdAt);
    }

    public void update(FintechUser fintechUser) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE fintech_user SET name = ?, email = ?, gender = ?, birth_date = ?, password_hash = ?, created_at = ? where id = ?");
        stm.setString(1, fintechUser.getName());
        stm.setString(2, fintechUser.getEmail());
        stm.setString(3, fintechUser.getGender());
        stm.setString(4, fintechUser.getBirthDate());
        stm.setString(5, fintechUser.getPasswordHash());
        stm.setString(6, fintechUser.getCreatedAt());
        stm.setInt(7, fintechUser.getId());
        stm.executeUpdate();
    }

    public void remove(long id) throws SQLException, EntityNotFoundException {
        PreparedStatement stm = connection.prepareStatement("DELETE from fintech_user where id = ?");
        stm.setLong(1, id);
        int line = stm.executeUpdate();
        if (line == 0)
            throw new EntityNotFoundException("Usuário não encontrado para ser removido");
    }

    public boolean valideUser(FintechUser fintechUser) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM fintech_user " + "WHERE email = ? AND password_hash = ?");
        stm.setString(1, fintechUser.getEmail());
        stm.setString(2, fintechUser.getPasswordHash());
        ResultSet result = stm.executeQuery();
        try {
            if (result.next()){
                System.out.println("existe");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stm.close();
                result.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}