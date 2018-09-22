package com.codecool.Queststore.DAO;

import com.codecool.Queststore.model.Session;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SessionDAO extends DAO<Session> {

    @Override
    protected void insertRecord(Session session) throws SQLException {
            PreparedStatement prepStatement = con.prepareStatement("INSERT INTO sessions VALUES (?, ?, ?)");
            prepStatement.setString(1,session.getSessionID());
            prepStatement.setString(2,localDateTimeToString(session.getExpirationDate()));
            prepStatement.setString(3,session.getUserID());
            prepStatement.executeQuery();
        }


    @Override
    protected void deleteRecord(Session session) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("DELETE FROM sessions WHERE session_id = ?");
        prepStatement.setString(1,session.getSessionID());
        prepStatement.executeQuery();
    }

    @Override
    protected void updateRecord(Session session) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("UPDATE sessions SET session_id = ? , expirationdate = ?,user_id = ? WHERE user_id = ?");
        prepStatement.setString(1,session.getSessionID());
        prepStatement.setString(2,localDateTimeToString(session.getExpirationDate()));
        prepStatement.setString(3,session.getUserID());
        prepStatement.setString(4,session.getUserID());
        prepStatement.executeQuery();
    }

    protected LocalDateTime stringToLocalDateTime(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(str, formatter);

    }

    protected String localDateTimeToString(LocalDateTime localDT){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return localDT.format(formatter);
    }

}