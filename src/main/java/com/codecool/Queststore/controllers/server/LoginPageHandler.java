package com.codecool.Queststore.controllers.server;

import com.codecool.Queststore.dao.PersonDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class LoginPageHandler extends AbstractHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/index.twig");
        JtwigModel model = JtwigModel.newModel().with("username", "Dupa");
        String response = template.render(model);
        if(method.equals("GET")){

            AbstractHandler.sendResponse(response, httpExchange);
        }

        System.out.println(method);
        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br);
            String formData = br.readLine();
            System.out.println(formData);
            Map data = parseFormData(formData);
            data.forEach((k,v) ->
                    System.out.println(k + "       " + v));
            if (!checkData(data)) {
                PersonDAO dao = new PersonDAO();
                HttpCookie cookie = createCookie();
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                String username = (String) data.get("username");
                switch (dao.getRoleByUsername(username)) {

                    case 0:
                        template = JtwigTemplate.classpathTemplate("templates/admin.twig.html");
                        model = JtwigModel.newModel().with("username", data.get("username"));
                        response = template.render(model);
                        AbstractHandler.sendResponse(response, httpExchange);
                        break;
                    default:
                        response = "Go away n00b!";
                        AbstractHandler.sendResponse(response, httpExchange);
                        break;
                }
            } else {
                response = "No way!";
                AbstractHandler.sendRedirectResponse(response, httpExchange);
            }
        }
    }

    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }

    private boolean checkData(Map<String, String> data) {
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
        else return false;
    }

    private HttpCookie createCookie() {
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
}

