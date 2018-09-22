package com.codecool.Queststore.DAO;
import com.codecool.Queststore.model.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonDAO extends DAO<User> {

    @Override
    protected void insertRecord(User user) throws SQLException {

        PreparedStatement prepStatement = con.prepareStatement("INSERT INTO person VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        prepStatement.setString(1, user.getUsername());
        prepStatement.setString(2, user.getPasswdhash());
        prepStatement.setString(3, user.getSalt());
        prepStatement.setString(4, user.getName());
        prepStatement.setString(5, user.getLastname());
        prepStatement.setInt(7, user.getRole());
        prepStatement.setString(8, user.getEmail());
        prepStatement.setString(9, user.getPhone());
        prepStatement.executeQuery();
    }

    @Override
    protected void deleteRecord(User user) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("DELETE FROM person WHERE email = ?");
        prepStatement.setString(1, user.getEmail());
        prepStatement.executeQuery();
    }

    @Override
    protected void updateRecord(User user) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("UPDATE person SET username = ?, passwd_hash =  ?," +
                " salt = ?, name = ?, last_name = ?, role = ?, email =  ?, phone =  ?)");
        prepStatement.setString(1, user.getUsername());
        prepStatement.setString(2, user.getPasswdhash());
        prepStatement.setString(3, user.getSalt());
        prepStatement.setString(4, user.getName());
        prepStatement.setString(5, user.getLastname());
        prepStatement.setInt(7, user.getRole());
        prepStatement.setString(8, user.getEmail());
        prepStatement.setString(9, user.getPhone());
        prepStatement.executeQuery();
    }
}