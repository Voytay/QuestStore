package com.codecool.Queststore.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginDAO extends Connectable {
    public String verification(String id){
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
        return null;
    }
}
