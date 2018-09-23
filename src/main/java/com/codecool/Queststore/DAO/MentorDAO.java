package com.codecool.Queststore.DAO;

import com.codecool.Queststore.mappers.UserMapper;
import com.codecool.Queststore.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MentorDAO extends DAO<User> {

    MentorDAO(){
        super.mapper = new UserMapper();
    }

    @Override
    public void insertRecord(User user) throws SQLException {
        int personID = getIdPerson(user);
        PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO mentor VALUES (?)");
        preparedStatement.setInt(1, personID);
        preparedStatement.executeQuery();
    }

    @Override
    public void deleteRecord(User user) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("DELETE FROM mentor WHERE email = ?");
        String email = user.getEmail();
        prepStatement.setString(1, email);
        prepStatement.executeQuery();
    }

    @Override
    public void updateRecord(User user) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("UPDATE mentor SET id_person = ?");
        int personID = getIdPerson(user);
        prepStatement.setInt(1, personID);
        prepStatement.executeQuery();

    }
    public int getIdPerson(User user) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("SELECT id_person FROM person WHERE email = ?");
        preparedStatement.setString(1, user.getEmail());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id_person");
        }
        return 0;
    }

}