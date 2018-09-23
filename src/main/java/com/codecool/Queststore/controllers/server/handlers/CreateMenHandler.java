package com.codecool.Queststore.controllers.server.handlers;

import com.codecool.Queststore.services.AdminServices;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CreateMenHandler extends AbstractHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException  {
        AdminServices adminServices = new AdminServices();

        String method = httpExchange.getRequestMethod();
        if(method.equals("GET")) {
            if (checkIsAdmin(httpExchange)) {
                sendResponse(adminServices.getCreateMenPage(), httpExchange, "/create_mentor");
            } else {
                sendRedirectResponse("", httpExchange, "/log");
            }
            }
        if(method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map<String, List<String>> mentors = httpExchange.getResponseHeaders();
            System.out.println(mentors.keySet().toString());
            Map<String, String> map = parseFormData(formData);
            try {
                adminServices.createMentor(map);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            }
        }
}