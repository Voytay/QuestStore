package com.codecool.Queststore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionDAO extends Connectable {

    public boolean createSession(String id, int userID) {
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("INSERT INTO session (session_id, user_id) VALUES ('" + id + "', '" + userID + "');");
        return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkSession(String sessionId) {
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("SELECT * FROM session WHERE session_id = '" + sessionId + "';");
            ResultSet rs = statement.getResultSet();
            if (rs.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getRoleBySession(String sessionId) {
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("SELECT user_id FROM session WHERE session_id = '" + sessionId + "';");
            ResultSet rs = statement.getResultSet();
            if(rs.next()) return rs.getInt("user_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getUserIdBySession(String sessionId){
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("SELECT user_id FROM session WHERE session_id = '" + sessionId + "';");
            ResultSet rs = statement.getResultSet();
            if(rs.next()) return rs.getInt("user_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}