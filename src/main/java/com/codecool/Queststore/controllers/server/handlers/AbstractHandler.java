package com.codecool.Queststore.controllers.server.handlers;


import com.codecool.Queststore.DAO.LoginDAO;
import com.codecool.Queststore.DAO.PersonDAO;
import com.codecool.Queststore.DAO.SessionDAO;
import com.codecool.Queststore.models.Session;
import com.codecool.Queststore.models.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractHandler {

    public static void sendResponse(String response, HttpExchange httpExchange, String path) {
        OutputStream os = httpExchange.getResponseBody();

        try {
            byte[] bs = response.getBytes();
            httpExchange.getResponseHeaders().set("Location", path);
            httpExchange.sendResponseHeaders(200, bs.length);
            os.write(bs);
            os.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void sendRedirectResponse(String response, HttpExchange httpExchange, String path) {
        OutputStream os = httpExchange.getResponseBody();
        try {
            byte[] bs = response.getBytes();
            httpExchange.getResponseHeaders().set("Location", path);
            httpExchange.sendResponseHeaders(302, -1);
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
        System.out.println("--------START OF CHECKDATA --------");
        String password = data.get("passwd");
        System.out.println("password: " + password);
        User user = null;
        try {
            user = dao.getPersonByLogin(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String salt = user.getSalt();
        String passwordHash = user.getPasswdhash();
        String hashed = "";

        String toCheck = password + salt;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(toCheck.getBytes(), 0, toCheck.length());
            hashed = new BigInteger(1, m.digest()).toString(16);
            System.out.println(hashed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("To check: " + passwordHash);
        System.out.println("Checked:  " + hashed);
        System.out.println("Is hash in db and hash from user equals?: " + hashed.equals(passwordHash));
        System.out.println("------END OF CHECKDATA---------");
        return hashed.equals(passwordHash);
    }

    public HttpCookie createCookie() {
        String date = String.valueOf(System.nanoTime());
        String hashed = "";
        System.out.println("--------START OF CREATE COOKIE----------");
        System.out.println("USED date:  " + date);
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(date.getBytes(), 0, date.length());
            hashed = new BigInteger(1, m.digest()).toString(16);
            System.out.println("hashed date:" + hashed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("---------END OF CREATECOOKIE--------");

        return new HttpCookie("Login", hashed);
    }

    public boolean checkCookie(String sessionId) throws SQLException {
        SessionDAO dao = new SessionDAO();
        return dao.checkSession(sessionId);
    }

    public boolean createSession(String sessionId, String username) {
        SessionDAO dao = new SessionDAO();
        PersonDAO personDAO = new PersonDAO();
int userID = 0;
        try {
            userID = personDAO.getPersonIDByLogin(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Session session = new Session(sessionId, userID);

        try {
            System.out.println("ZAPISZ");
            dao.insertRecord(session);
            System.out.println("ZAPISANE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkSession(String sessionId) throws SQLException {
        LoginDAO dao = new LoginDAO();
        return dao.verification(sessionId);
    }

//    public String parseCookies(List<String> cookies) {
//        String[] pair;
//        String value = null;
//        for (String s : cookies) {
//            System.out.println(s);
//            if (s.matches("Login.*")) {
//                System.out.println("matched");
//                System.out.println(s);
//                pair = s.split("=");
//                value = pair[1];
//                value = value.replaceAll("^\"|\"$", "");
//                return value;
//            }
//        }
//        return value;
//    }

    public String parseCookies(List<String> cookies) {
        String[] pair;
        String value;
        String[] pair2;
        for (String s : cookies) {
            if (s.matches(".*Login.*") && !s.equals("Login=")) {
                if (s.contains(";")) {
                    pair = s.split(";");
                    value = pair[1];
                    pair2 = value.split("=");
                    value = pair2[1];
                    value = value.replaceAll("^\"|\"$", "");
                } else {
                    pair = s.split("=");
                    value = pair[1];
                    value = value.replaceAll("^\"|\"$", "");
                    return value;
                }
            }
        }
            return null;
    }

    public boolean checkIsAdmin(HttpExchange httpExchange) {
        return  (isUserLogged(httpExchange) && isAdminUser(httpExchange));
    }

    public String parseCookie(String cookie) {
        String[] pair;
        String value = null;
        if (cookie.matches("Login.*")) {
            System.out.println("cookie OK!");
            pair = cookie.split("=");
            value = pair[1];
            value = value.replaceAll("^\"|\"$", "");
            return value;
        }
        return value;
    }

    public int checkRole(String sessionId) {
        SessionDAO dao = new SessionDAO();
        User user = null;
        String username="";
        try {
            username = dao.getUserNamebySession(sessionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PersonDAO personDAO = new PersonDAO();
        try {
            user = personDAO.getPersonByLogin(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user.getRole();

    }

    public boolean isUserLogged(HttpExchange httpExchange) {
        List<String> cookies = httpExchange.getRequestHeaders().get("Cookie");
        String sessionID = "";
        if (cookies != null) {
            System.out.println("COOKIES NIE NULL");
            sessionID = parseCookies(cookies);
            System.out.println("Session id: " + sessionID);
        }
        boolean toReturn = false;
        try {
            return checkSession(sessionID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DO KOŃCA METODY");
        return toReturn;

    }

    public boolean isAdminUser(HttpExchange httpExchange) {
        List<String> cookies = httpExchange.getRequestHeaders().get("Cookie");
        String sessionID = "";
        if (cookies != null) {
            sessionID = parseCookies(cookies);
        }
        SessionDAO dao = new SessionDAO();
        return checkRole(sessionID) == 1;
    }

    public String setProperPath(String username) {
        PersonDAO dao = new PersonDAO();
        User user = null;
        try {
           user = dao.getPersonByLogin(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String path = "0";
        int role = user.getRole();

        switch (role) {
            case 1:
                System.out.println("Admin opened page");
                path = "/admin";
                break;
            case 2:
                System.out.println("Mentor opened page");
                path = "/mentor";
                break;
            case 3:
                System.out.println("Student opened page");
                path = "/student";
                break;
            default:
                System.out.println("Dupa i gunwo - wypierdalaj!");
                break;
        }
        return path;
    }

    protected static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }
}
