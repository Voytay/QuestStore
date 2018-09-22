package com.codecool.Queststore.mappers;

import com.codecool.Queststore.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        String username = resultSet.getString("username");
        String passwd_hash = resultSet.getString("passwd_hash");
        String salt = resultSet.getString("salt");
        String name = resultSet.getString("name");
        String last_name = resultSet.getString("last_name");
        int role = resultSet.getInt("role");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");

        return new User(username, passwd_hash, salt, name, last_name, role, email, phone);
    }
}
