package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {

    public static Connection getConnect(String url, String userName, String password) {
        Connection connection = null;
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(url, userName, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Connect failure!");
        }
//        catch (ClassNotFoundException e) {
//            System.out.println("Error driver!");
//        }
        return connection;
    }
}
