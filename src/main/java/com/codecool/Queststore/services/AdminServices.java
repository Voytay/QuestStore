package com.codecool.Queststore.services;

import com.codecool.Queststore.DAO.MentorDAO;
import com.codecool.Queststore.DAO.SessionDAO;
import com.codecool.Queststore.models.User;
import com.codecool.Queststore.queries.AllMentors;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AdminServices extends Services {
    private final int ADMIN = 1;
    private final int MENTOR = 2;


    public String getCreateMenPage() {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("static/templates/create_mentor.twig.html");
            JtwigModel model = JtwigModel.newModel().with("username", "Beniz");
            String response = template.render(model);
            return response;
    }

    public void createMentor(Map<String, String> data) throws SQLException {
        User user = createUser(data, MENTOR);
        MentorDAO mentorDAO = new MentorDAO();
        mentorDAO.insertRecord(user);
    }

    public String getShowMenPage(String sessionID) throws SQLException {
        SessionDAO sessionDAO = new SessionDAO();
        String username = sessionDAO.getUserNamebySession(sessionID);
        MentorDAO menDao = new MentorDAO();
        List<User> mentors = menDao.getRecordsList(new AllMentors());
        JtwigTemplate template = JtwigTemplate.classpathTemplate("static/templates/show_mentors.twig.html");
        JtwigModel model = JtwigModel.newModel().with("username", username);
           model.with("mentors", mentors);
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