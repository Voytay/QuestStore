package com.codecool.Queststore.controllers.server.handlers;

import com.codecool.Queststore.controllers.server.AbstractHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;

public class SessionHandler extends AbstractHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        List<String> cookie = httpExchange.getResponseHeaders().get("Cookie");
        String sessionId = parseCookie(cookie);

        System.out.println(checkSession(sessionId));
        System.out.println(checkRole(sessionId));
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

  /*  private boolean checkSession(String sessionId) {
        LoginDAO dao = new LoginDAO();
        if (dao.verification(sessionId)) return true;
        else return false;
    }
    */


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
