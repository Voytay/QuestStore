package com.codecool.Queststore.controllers.server.handlers;

import com.codecool.Queststore.controllers.server.AbstractHandler;
import com.codecool.Queststore.dao.LoginDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.util.List;

public class SessionHandler extends AbstractHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";

        List<String> cookies = httpExchange.getRequestHeaders().get("Cookie");
        boolean isLogged = false;
        String sessionId;
        if (cookies != null) {
            sessionId = parseCookie(cookies);
            System.out.println(checkSession(sessionId));

            if (checkSession(sessionId)) isLogged = true;
            if (isLogged) {
                response = "You are logged in!";
                if (checkRole(sessionId) == 1) {
                    response += "</br> You are admin!";
                    response += "your sessionId is:";
                    response += sessionId;
                } else response += "<br> You are not admin!";
                AbstractHandler.sendResponse(response, httpExchange);

            }
        } else {

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/index.twig.html");
            JtwigModel model = JtwigModel.newModel().with("username", "Dupa");
            response = template.render(model);
            AbstractHandler.sendRedirectResponse(response, httpExchange);

        }
    }

    public String parseCookie(List<String> cookies) {
        String[] pair;
        String value = null;
        for (String s : cookies) {
            System.out.println(s);
            if (s.matches("Login.*")) {
                System.out.println("matched");
                System.out.println(s);
                pair = s.split("=");
                value = pair[1];
                value = value.replaceAll("^\"|\"$", "");
                return value;
            }
        }
        return value;
    }

    private boolean checkSession(String sessionId) {
        LoginDAO dao = new LoginDAO();
        if (dao.verification(sessionId)) return true;
        else return false;
    }

    public int checkRole(String sessionId) {
        LoginDAO dao = new LoginDAO();
        return dao.getRoleBySession(sessionId);

    }
/*
    public boolean createSession(String sessionId){
        LoginDAO dao = new LoginDAO();
        return dao.createSession(sessionId);
    }

    public boolean login(String username, String password){
        LoginDAO dao = new LoginDAO();
        return dao.login();
    }
*/

}
