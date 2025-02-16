package br.com.fintech.fintechweb.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USUARIO = "usuario";
    private static final String SENHA = "senha";
//    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
//    private static final String USUARIO = "sys as sysdba";
//    private static final String SENHA = "oracle";
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}