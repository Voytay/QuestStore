package com.codecool.Queststore.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginDAO extends Connectable {
    public String verification(String id) throws SQLException {
        String userID;
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("SELECT id FROM session WHERE " +
                    "session_id = " + id + ";");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                userID = resultSet.getString("id");
                return userID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        con.close();
        return null;
    }
    public int getRoleBySession(String session_id) throws SQLException {
        String userID;
        int personRole;
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("SELECT user_id FROM session WHERE " +
                    "session_id = " + session_id + ";");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                userID = resultSet.getString("user_id");
                statement.execute("SELECT role FROM person WHERE " +
                        "user_id = " + userID + ";");
                        resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    personRole = resultSet.getInt("session_id");
                    return personRole;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        con.close();
        System.out.println("No such session or person");
        return 0;
    }
}
