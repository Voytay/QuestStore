package com.codecool.Queststore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonDAO extends Connectable {

    public String getSalt(String username) {
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("SELECT salt FROM person WHERE " +
                    "username = '" + username + "';");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getString("salt");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPasswordHash(String username) {
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("SELECT passwd_hash FROM person WHERE " +
                    "username = '" + username + "';");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getString("passwd_hash");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getRoleByUsername(String username) {
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("SELECT role FROM person WHERE " +
                    "username = '" + username + "';");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                System.out.println(resultSet.getInt("role"));
                return resultSet.getInt("role");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getIdByUsername(String username) {
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("SELECT id_person FROM person WHERE " +
                    "username = '" + username + "';");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getInt("id_person");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getUsernameByID(int userID) {
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("SELECT username FROM person WHERE " +
                    "id_person = '" + userID + "';");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getString("username");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
