package com.codecool.Queststore.services;
import org.jtwig.*;

public class LoginServices {
    private final int ADMIN = 1;
    private final int MENTOR = 2;
    private final int STUDENT = 3;

    public String getWelcomePage(User user) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/" + getFileName(user.getROLE()));
            JtwigModel model = JtwigModel.newModel().with("name", user.getNAME());
            String response = template.render(model);
            return response;
    }

    private String getFileName(int role) {
        if(role == ADMIN) return "admin.twig";
        else if(role == MENTOR) return "mentor.twig";
        else if (role == STUDENT) return "student.twig";
        return "login.twig";
    }
}
