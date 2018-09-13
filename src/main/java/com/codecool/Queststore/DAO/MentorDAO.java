package com.codecool.Queststore.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MentorDAO extends Connectable {

    public void createMentor(String username, String passwd_hash, String salt, String name,
                             String last_name, int role, String email, String phone) {
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("INSERT INTO person VALUES ('" + username +
                    "', '" + passwd_hash + "', '" + salt + "', '" + name + "', '" +
                    last_name + "', '" + role + "', '" + email + "', '" + phone + "')");
            statement.execute("SELECT id_person FROM person WHERE email ='" + email + "';");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                String personId = resultSet.getString("id_person");
                statement.execute("INSERT INTO mentor VALUES ('" + personId + "';");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getMentors() {
        Connection con = getConnection();
        int id;
        String name;
        String lastName;
        String username;
        String pass;
        String phone;
        String email;
        int role;
        List<User> mentorList = new ArrayList<User>();
        try {
            Statement statement = con.createStatement();
            statement.execute("SELECT * FROM person WHERE role = '2';");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                id = resultSet.getInt("id_person");
                name= resultSet.getString("name");
                lastName= resultSet.getString("last_name");
                username = resultSet.getString("username");
                pass = resultSet.getString("passwd_hash");
                phone = resultSet.getString("phone");
                email = resultSet.getString("email");
                role = resultSet.getInt("role");
                User mentor = new User(id,name,lastName,username,pass,phone,email,role);
                mentorList.add(mentor);
            }
            con.close();
            return mentorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}