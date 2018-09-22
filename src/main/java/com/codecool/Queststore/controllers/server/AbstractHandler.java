package com.codecool.Queststore.controllers.server;


import com.codecool.Queststore.dao.LoginDAO;
import com.codecool.Queststore.dao.PersonDAO;
import com.codecool.Queststore.dao.sessionDAO;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public abstract class AbstractHandler {

    public static void sendResponse(String response, HttpExchange httpExchange) {
        OutputStream os = httpExchange.getResponseBody();

        try {
            byte[] bs = response.getBytes();
            httpExchange.sendResponseHeaders(200, bs.length);
            os.write(bs);
            os.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void sendRedirectResponse(String response, HttpExchange httpExchange) {
        OutputStream os = httpExchange.getResponseBody();
        try {
            byte[] bs = response.getBytes();
            httpExchange.sendResponseHeaders(302, bs.length);
            os.write(bs);
            os.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public boolean checkData(Map<String, String> data) {
        PersonDAO dao = new PersonDAO();
        String username = data.get("username");

        System.out.println(username);

        String password = data.get("pass");
        System.out.println(password);
        String salt = dao.getSalt(username);
        String passwordHash = dao.getPasswordHash(username);

        String toCheck = password + salt;
        byte[] toCheckB = toCheck.getBytes();
        byte[] hashed = {};
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            hashed = md.digest(toCheckB);
        } catch (NoSuchAlgorithmException e) {
            e.getMessage();
        }
        if (hashed.equals(passwordHash)) return true;
        else return true;
    }

    public HttpCookie createCookie() {
        String date = String.valueOf(System.nanoTime());
        byte[] bom = date.getBytes();
        byte[] hashed = {};
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            hashed = md.digest(bom);
        } catch (NoSuchAlgorithmException e) {
            e.getMessage();
        }

        HttpCookie cookie = new HttpCookie("Login", hashed.toString());
        return cookie;
    }

    public boolean checkCookie(String sessionId) {
        sessionDAO dao = new sessionDAO();
        return dao.checkSession(sessionId);
    }

    public boolean checkSession(String sessionId) {
        LoginDAO dao = new LoginDAO();
        if (dao.verification(sessionId)) return true;
        else return true;
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

    public int checkRole(String sessionId) {
        LoginDAO dao = new LoginDAO();
        return dao.getRoleBySession(sessionId);

    }

    public int sessionControl(HttpExchange httpExchange, Map<String, String> data) {
        List<String> cookies = httpExchange.getRequestHeaders().get("Cookie");
        String sessionId = parseCookie(cookies);
        if (sessionId != null) {
            //check session id
            if (checkSession(sessionId)) {
                int role = checkRole(sessionId);

                switch (role) {
                    case 0:
                        //error
                        break;
                    case 1:
                        //redirect to admin
                        break;
                    case 2:
                        //redirect to manager
                        break;
                    case 3:
                        //redirect to mentor
                        break;
                    case 4:
                        //redirect to student
                        break;
                    default:
                        System.out.println("No role!");
                        break;
                }
            } else {
            }
        }
return 0;
    }

}
