package com.codecool.Queststore.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SessionDAO extends DAO {

    public void createSession(String id, int userID) throws SQLException {

        executeQuery("INSERT INTO session (session_id, expirationdate, user_id)\n" +
                "VALUES ('" + id + "', '" + userID + "');");

    }
}