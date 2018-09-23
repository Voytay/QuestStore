package com.codecool.Queststore.controllers.server.handlers;

import com.codecool.Queststore.services.AdminServices;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.sql.SQLException;
import java.util.List;

public class ShowMenHandler extends AbstractHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) {
        AdminServices adminServices = new AdminServices();
        String method = httpExchange.getRequestMethod();
        List<String> cookies = httpExchange.getRequestHeaders().get("Cookie");
        if(method.equals("GET")) {
            if(checkIsAdmin(httpExchange)) {
                try {
                    String sessionID = parseCookies(cookies);
                    sendResponse(adminServices.getShowMenPage(sessionID), httpExchange, "/show_mentors");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                sendRedirectResponse("", httpExchange, "/log");
            }
        }
        }
}