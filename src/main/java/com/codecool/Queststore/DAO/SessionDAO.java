package com.codecool.Queststore.DAO;

import com.codecool.Queststore.models.Session;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SessionDAO extends DAO<Session> {

    @Override
    public void insertRecord(Session session) throws SQLException {
            PreparedStatement prepStatement = con.prepareStatement("INSERT INTO sessions(session_id,expirationdate,user_id) VALUES (?, ?, ?)");
        System.out.println("INSERTS ))))))))))))))))))))))))))))))))))))))))))))))))");
            prepStatement.setString(1,session.getSessionID());
            prepStatement.setString(2,localDateTimeToString(session.getExpirationDate()));
            prepStatement.setInt(3,session.getUserID());
            prepStatement.executeUpdate();
        System.out.println("EXECUTED *********************************");
        }


    @Override
    public void deleteRecord(Session session) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("DELETE FROM sessions WHERE session_id = ?");
        prepStatement.setString(1,session.getSessionID());
        prepStatement.executeUpdate();
    }

    @Override
    public void updateRecord(Session session) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("UPDATE sessions SET session_id = ? , expirationdate = ?,user_id = ? WHERE user_id = ?");
        prepStatement.setString(1,session.getSessionID());
        prepStatement.setString(2,localDateTimeToString(session.getExpirationDate()));
        prepStatement.setInt(3,session.getUserID());
        prepStatement.setInt(4,session.getUserID());
        prepStatement.executeUpdate();
    }

    protected LocalDateTime stringToLocalDateTime(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(str, formatter);

    }

    public String localDateTimeToString(LocalDateTime localDT){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return localDT.format(formatter);
    }

    public boolean checkSession(int sessionId, String username) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("SELECT * FROM sessions WHERE session_id = ? AND username = ?");
        prepStatement.setInt(1, sessionId);
        prepStatement.setString(2, username);
        ResultSet resultSet = prepStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public boolean checkSession(String sessionId) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("SELECT * FROM sessions WHERE session_id = ? AND username = ?");
        prepStatement.setString(1, sessionId);
        ResultSet resultSet = prepStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public String getUserNamebySession(String sessionId) throws SQLException {
        int userID = 0;
        PreparedStatement prepStatement = con.prepareStatement("SELECT user_id FROM sessions WHERE session_id = ?");
        prepStatement.setString(1, sessionId);
        ResultSet resultSet = prepStatement.executeQuery();
        if (resultSet.next()) {
            userID = resultSet.getInt("user_id");
        }
        prepStatement = con.prepareStatement("SELECT username FROM person WHERE id_person = ?");
        prepStatement.setInt(1,userID);
        resultSet = prepStatement.executeQuery();
        if(resultSet.next()){
            return resultSet.getString("username");
        }
        else return null;
        }
    }