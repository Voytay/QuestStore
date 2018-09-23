package com.codecool.Queststore.services;

import com.codecool.Queststore.DAO.MentorDAO;
import com.codecool.Queststore.models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Map;

public class AdminServices {
    private final int ADMIN = 1;

//    public String getCreateMentPage(User user) {
//        if(user.getROLE() == ADMIN) {
//            JtwigTemplate template = JtwigTemplate.classpathTemplate("static/templates/create_mentor.twig.html");
//            JtwigModel model = JtwigModel.newModel().with("name", user.getNAME());
//            String response = template.render(model);
//            return response;
//        }
//        return null;
//    }

    public String getCreateMenPage() {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("static/templates/create_mentor.twig.html");
            JtwigModel model = JtwigModel.newModel().with("username", "Beniz");
            String response = template.render(model);
            return response;
    }

    public String hashPassword(String password, String salt) {
        String toHash = password + salt;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(toHash.getBytes(), 0, toHash.length());
            String  hashed = new BigInteger(1, m.digest()).toString(16);
            System.out.println(hashed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toHash;
    }

    public String getRandomSalt() {
        return RandomStringUtils.randomAlphanumeric(5);
    }

    public void createMentor(Map<String, String> data) throws SQLException {
        int role = 2;
        String salt = getRandomSalt();
        String passwdHash = hashPassword(data.get("password"), salt);
        User user = new User(data.get("username"), passwdHash, salt, data.get("first_name"),
                data.get("last_name"), role, data.get("email"), data.get("phone_number"));
        MentorDAO mentorDAO = new MentorDAO();
        mentorDAO.insertRecord(user);
    }

    public String getShowMenPage() {
//            MentorDAO menDao = new MentorDAO();
//            List<User> mentors = menDao.getMentors();
            JtwigTemplate template = JtwigTemplate.classpathTemplate("static/templates/show_mentors.twig.html");
            JtwigModel model = JtwigModel.newModel().with("name", "Beniz");
            //model.with("mentors", mentors);
            String response = template.render(model);
            return response;
    }

    public String getEditMenPage() {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("static/templates/edit_mentor.twig.html");
            JtwigModel model = JtwigModel.newModel().with("name", "Beniz");
            String response = template.render(model);
            return response;
    }

    public String getManageClassPage() {
            //ClassDAO classDao = new ClassDAO();
            //ArrayList<Class> classes = classDao.getClasses();
            JtwigTemplate template = JtwigTemplate.classpathTemplate("static/templates/manage_class.twig.html");
            JtwigModel model = JtwigModel.newModel().with("name", "Bezin");
            //model.with("classes", classes);
            String response = template.render(model);
            return response;
    }




}