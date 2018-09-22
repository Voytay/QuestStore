package com.codecool.Queststore.DAO;

import com.codecool.Queststore.model.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends DAO<Session> {
    public boolean verification(String id) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("SELECT session_id FROM sessions WHERE session_id = ?");
        prepStatement.setString(1, id);
        ResultSet resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString("session_id").equals(id)) return true;

            }
        return false;
    }

    @Override
    protected void insertRecord(Session record) throws SQLException {
    }

    @Override
    protected void deleteRecord(Session record) throws SQLException {

    }

    @Override
    protected void updateRecord(Session record) throws SQLException {

    }
}