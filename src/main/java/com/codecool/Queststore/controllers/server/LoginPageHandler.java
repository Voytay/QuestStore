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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginPageHandler extends AbstractHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        JtwigTemplate template = JtwigTemplate.classpathTemplate("static/templates/index.twig.html");
        JtwigModel model = JtwigModel.newModel().with("username", "Dupa");
        String response = template.render(model);

        boolean isLoged = false;
        List<String> cookies = httpExchange.getRequestHeaders().get("Cookie");
        if (cookies != null) {
            String sessionId = parseCookie(cookies);
            isLoged = checkSession(sessionId);
        }

        if (method.equals("GET")) {
            if (isLoged) {
                PersonDAO dao = new PersonDAO();
                Map<String, String> dataa = new HashMap<>();
                dataa.put("username", "dupa");

                switch (dao.getRoleByUsername(dataa.get("username"))) {
                    case 1:
                        template = JtwigTemplate.classpathTemplate("static/templates/admin.twig.html");
                        model = JtwigModel.newModel().with("username", dataa.get("username"));
                        response = template.render(model);
                        System.out.println("Admin opened page");
                        break;
                    default:
                        System.out.println("Dupa i gunwo");
                        break;
                }
            } else {

                System.out.println("No logged in user opened page");
            }
            sendResponse(response, httpExchange);
        }

        System.out.println(method);
        if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map data = parseFormData(formData);

            if (isLoged) {
                PersonDAO dao = new PersonDAO();
                switch (dao.getRoleByUsername((String) data.get("username"))) {
                    case 1:
                        template = JtwigTemplate.classpathTemplate("static/templates/admin.twig.html");
                        model = JtwigModel.newModel().with("username", data.get("username"));
                        response = template.render(model);
                        break;
                    default:
                        response = "Go away n00b!";
                        break;
                }
                sendResponse(response, httpExchange);
            }


            if (checkData(data) && !isLoged) {
                PersonDAO dao = new PersonDAO();
                HttpCookie cookie = createCookie();
                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                String username = (String) data.get("username");
                switch (dao.getRoleByUsername(username)) {

                    case 1:
                        template = JtwigTemplate.classpathTemplate("static/templates/admin.twig.html");
                        model = JtwigModel.newModel().with("username", data.get("username"));
                        response = template.render(model);
                        break;
                    default:
                        response = "Go away n00b!";
                        break;
                }
                AbstractHandler.sendResponse(response, httpExchange);

            } else {

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


}

