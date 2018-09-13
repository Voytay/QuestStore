package com.codecool.Queststore.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MentorDAO extends Connectable {

    public void createMentor(String username, String passwd_hash, String salt, String name,
                             String last_name, int role, String email, String phone){
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("INSERT INTO person VALUES ('" + username +
                    "', '" + passwd_hash + "', '" + salt + "', '" + name + "', '" +
                    last_name + "', '" + role + "', '" + email + "', '" + phone +"')");
            statement.execute("SELECT id_person FROM person WHERE email ='" + email + "';");
            ResultSet resultSet = statement.getResultSet();

            if(resultSet.next()) {
                String personId = resultSet.getString("id_person");
                statement.execute("INSERT INTO mentor VALUES ('" + personId + "';");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }
