package com.codecool.Queststore.DAO;
import com.codecool.Queststore.models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAO extends DAO<User> {

    @Override
    public void insertRecord(User user) throws SQLException {

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
    public void deleteRecord(User user) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("DELETE FROM person WHERE email = ?");
        prepStatement.setString(1, user.getEmail());
        prepStatement.executeQuery();
    }

    @Override
    public void updateRecord(User user) throws SQLException {
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

    public User getPersonByLogin(String login) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("SELECT * FROM person WHERE username = ? ");
        prepStatement.setString(1, login);
        ResultSet resultSet = prepStatement.executeQuery();
        if (resultSet.next()) {
            String username = resultSet.getString("username");
            String passwdHash = resultSet.getString("passwd_hash");
            String salt = resultSet.getString("salt");
            String name = resultSet.getString("name");
            String lastName = resultSet.getString("last_name");
            int role = resultSet.getInt("role");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            User user = new User(username, passwdHash, salt, name, lastName, role, email, phone);
            return user;
        }
        return null;
    }

    public int getPersonIDByLogin(String login) throws SQLException {
        PreparedStatement prepStatement = con.prepareStatement("SELECT * FROM person WHERE username = ? ");
        prepStatement.setString(1, login);
        ResultSet resultSet = prepStatement.executeQuery();
        if (resultSet.next()) {
            int ID = resultSet.getInt("id_person");
            return ID;
        }
        return 0;
    }
}