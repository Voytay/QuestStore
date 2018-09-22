package com.codecool.Queststore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginDAO extends Connectable {
    public boolean verification(String id) {
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("SELECT session_id FROM session WHERE " +
                    "session_id = '" + id + "';");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                if (resultSet.getString("session_id").equals(id)) return true;

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getRoleBySession(String session_id) {
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("SELECT user_id FROM session WHERE " +
                    "session_id = '" + session_id + "';"); //                   USER ID
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                String userID = resultSet.getString("user_id");


                statement.execute("SELECT role FROM person WHERE " +
                        "id_person = '" + userID + "';");  //               ROLE
                resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    return resultSet.getInt("role");

                }
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("No such session or person");
        return 0;
    }
}