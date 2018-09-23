package com.codecool.Queststore.controllers.server.handlers;

import com.codecool.Queststore.services.AdminServices;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CreateMenHandler extends AbstractHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) {
        String method = httpExchange.getRequestMethod();
        if(method.equals("GET")) {
            AdminServices adminServices = new AdminServices();
            sendResponse(adminServices.getCreateMenPage(), httpExchange, "/create_mentor");
        }
        if(method.equals("POST")) {
            AdminServices adminServices = new AdminServices();
            //adminServices.createMentor(Maaaapa);
        }
        }
}