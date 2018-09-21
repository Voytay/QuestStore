package com.codecool.Queststore.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonDAO extends DAO{

    public String getSalt(String username) throws SQLException {

            ResultSet resultSet = executeQuery("SELECT salt FROM person WHERE " +
                    "username = '" + username + "';");
            if (resultSet.next()) {
                 return resultSet.getString("salt");
            }
        return null;
    }

    public String getPasswordHash(String username) throws SQLException {

            ResultSet resultSet = executeQuery("SELECT passwd_hash FROM person WHERE " +
                    "username = '" + username + "';");
            if (resultSet.next()) {
                return resultSet.getString("passwd_hash");
            }
        return null;
    }

    public String getRole(String username) throws SQLException {

            ResultSet resultSet = executeQuery("SELECT role FROM person WHERE " +
                    "username = '" + username + "';");
            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        return null;
    }
}
