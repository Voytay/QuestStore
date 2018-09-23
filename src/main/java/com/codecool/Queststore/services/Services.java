package com.codecool.Queststore.services;

import com.codecool.Queststore.DAO.PersonDAO;
import com.codecool.Queststore.models.User;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Map;

public abstract class Services {

    public User createUser(Map<String, String> data, int role) throws SQLException {
        String salt = getRandomSalt();
        String passwdHash = hashPassword(data.get("password"), salt);
        User user = new User(data.get("username"), passwdHash, salt, data.get("fname"),
                data.get("lname"), role, data.get("email"), data.get("phone"));
        PersonDAO personDAO = new PersonDAO();
        personDAO.insertRecord(user);
        return user;
    }

    public String hashPassword(String password, String salt) {
        String toHash = password + salt;
        String hashed = "";
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(toHash.getBytes(), 0, toHash.length());
            hashed = new BigInteger(1, m.digest()).toString(16);
            System.out.println(hashed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashed;
    }

    public String getRandomSalt() {
        return RandomStringUtils.randomAlphanumeric(5);
    }

}
