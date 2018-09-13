package com.codecool.Queststore.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionDAO extends Connectable {

    public void createSession(String id, int userID) {
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("INSERT INTO session (session_id, expirationdate, user_id)\n" +
                    "VALUES (" + id + ", " + userID + ");");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}