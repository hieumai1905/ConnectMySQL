package org.example;

import java.sql.*;

public class Main {
    public static final String URL = "jdbc:mysql://localhost:3306/demo";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "1905";

    public static void main(String[] args) throws SQLException {
        Connection con = ConnectDatabase.getConnect(URL, USER_NAME, PASSWORD);
        if (con == null) {
            System.out.println("Close!");
            System.exit(0);
        }
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(name, email) VALUES(?, ?)");
            ps.setString(1, "Chu thi thuc anh");
            ps.setString(2, "thucanh@gmail.com");
            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            if (con != null) {
                try {
                    System.out.println("Rollback!");
                    con.rollback();
                    con.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        try {
            Statement sttm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM users";
            ResultSet rs = sttm.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("email"));
            }
            rs.close();
            sttm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}