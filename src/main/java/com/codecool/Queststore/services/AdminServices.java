package com.codecool.Queststore.services;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.util.ArrayList;

public class AdminServices {
    private final int ADMIN = 1;

    public String getCreateMentPage(User user) {
        if(user.getROLE() == ADMIN) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/createMentors.twig");
            JtwigModel model = JtwigModel.newModel().with("name", user.getNAME());
            String response = template.render(model);
            return response;
        }
        return null;
    }

    public String getShowMentPage(User user) {
        if(user.getROLE() == ADMIN) {
            MentorDao menDao = new MentorDao();
            ArrayList<User> mentors = menDao.getMentors();
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/showMentors.twig");
            JtwigModel model = JtwigModel.newModel().with("name", user.getNAME());
            model.with("mentors", mentors);
            String response = template.render(model);
            return response;
        }
        return null;
    }

    public String getEditMentPage(User user) {
        if (user.getROLE() == ADMIN) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/editMentors.twig");
            JtwigModel model = JtwigModel.newModel().with("name", user.getNAME());
            String response = template.render(model);
            return response;
        }
        return null;
    }

    public String getManageClassPage(User user) {
        if (user.getROLE() == ADMIN) {
            ClassDao classDao = new ClassDao();
            ArrayList<Class> classes = classDao.getClasses();
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/manageClass.twig");
            JtwigModel model = JtwigModel.newModel().with("name", user.getNAME());
            model.with("classes", classes);
            String response = template.render(model);
            return response;
        }
        return null;
    }
}
