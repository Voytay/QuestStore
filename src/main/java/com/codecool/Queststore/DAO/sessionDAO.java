package com.codecool.Queststore.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class sessionDAO extends Connectable {

    public void createSession(String id, LocalDate date, int UserID){
    Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("INSERT INTO session (session_id, expirationdate, user_id)\n" +
                    "VALUES (id, date, UserID);" +
                    "session_id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
