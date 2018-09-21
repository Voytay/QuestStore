package com.codecool.Queststore.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MentorDAO extends DAO {

    public void createMentor(String username, String passwd_hash, String salt, String name,
                             String last_name, int role, String email, String phone) throws SQLException {

        String insertMentorToPersonQuery = ("\"INSERT INTO person VALUES ('\" + username +\n" +
                "                \"', '\" + passwd_hash + \"', '\" + salt + \"', '\" + name + \"', '\" +\n" +
                "                last_name + \"', '\" + role + \"', '\" + email + \"', '\" + phone + \"')\"");
        String getMentorIdQuery = ("SELECT id_person FROM person WHERE email ='\" + email + \"';");
        executeQuery(insertMentorToPersonQuery);
        ResultSet mentorIdSet = executeQuery(getMentorIdQuery);
        if (mentorIdSet.next()) {
            String personId = mentorIdSet.getString("id_person");
            String insertMentorToMentorsQuery = ("INSERT INTO mentor VALUES ('" + personId + "';");
            executeQuery(insertMentorToMentorsQuery);
        }
    }

    public List<User> getMentors() throws SQLException {
        int id;
        String name;
        String lastName;
        String username;
        String pass;
        String phone;
        String email;
        int role;
        List<User> mentorList = new ArrayList<User>();
        ResultSet resultSet = executeQuery("SELECT * FROM person WHERE role = '2';");
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
            return mentorList;
    }
}