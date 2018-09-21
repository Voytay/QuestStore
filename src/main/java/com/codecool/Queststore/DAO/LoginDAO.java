package com.codecool.Queststore.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends DAO {
    public boolean verification(String id) throws SQLException {

        ResultSet resultSet = executeQuery("SELECT session_id FROM session WHERE " +
                    "session_id = '" + id + "';");
            if (resultSet.next()) {
                if (resultSet.getString("session_id").equals(id)) return true;

            }
        return false;
    }

    public int getRoleBySession(String session_id) throws SQLException {

        ResultSet resultSet = executeQuery("SELECT user_id FROM session WHERE " +
                    "session_id = '" + session_id + "';"); //                   USER ID
            if (resultSet.next()) {
                String userID = resultSet.getString("user_id");

                ResultSet rs = executeQuery("SELECT role FROM person WHERE " +
                        "id_person = '" + userID + "';");  //               ROLE
                if (rs.next()) {
                    return rs.getInt("role");

                }
            }
        System.out.println("No such session or person");
        return 0;
    }
}