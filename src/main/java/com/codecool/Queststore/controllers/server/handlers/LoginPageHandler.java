package com.codecool.Queststore.controllers.server.handlers;

import com.codecool.Queststore.DAO.PersonDAO;
import com.codecool.Queststore.DAO.SessionDAO;
import com.codecool.Queststore.models.User;
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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginPageHandler extends AbstractHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        JtwigTemplate template = JtwigTemplate.classpathTemplate("static/templates/index.twig.html");
        JtwigModel model = JtwigModel.newModel().with("username", "Dupa");
        String response = template.render(model);
        System.out.println("A TU?");

        boolean isLoged = isUserLogged(httpExchange);
        System.out.println("BEEEEEEEZIN");
        System.out.println(isLoged);
        List<String> cookies = httpExchange.getRequestHeaders().get("Cookie");
        String path = "";
        if (method.equals("GET")) {
            if (isUserLogged(httpExchange)) {
                SessionDAO dao = new SessionDAO();
                PersonDAO pDao = new PersonDAO();
                String sessionID = parseCookies(cookies);

                String username = null;
                try {
                    username = dao.getUserNamebySession(sessionID);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int userId = 0;
                try {
                    userId = pDao.getPersonIDByLogin(username);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                path = setProperPath(username);
                sendRedirectResponse(response, httpExchange, path);

            } else {
                path = "/log";
                System.out.println("No logged-in user opened page");
                sendResponse(response, httpExchange, path);
            }
        }

        if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map data = parseFormData(formData);

            if (isLoged) {
                PersonDAO dao = new PersonDAO();
                String username = (String) data.get("username");
                path = setProperPath(username);
                sendRedirectResponse(response, httpExchange, path);
            } else if (checkData(data)) {
                PersonDAO dao = new PersonDAO();
                HttpCookie cookie = createCookie();

                httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
                String username = (String) data.get("username");
                String sessionId = parseCookie(cookie.toString());
                createSession(sessionId, username);

                path = setProperPath(username);
                sendRedirectResponse(response, httpExchange, path);

            } else {
                path = "/log";
                System.out.println("uj");

                sendRedirectResponse(response, httpExchange, path);
            }
        }
    }




}

