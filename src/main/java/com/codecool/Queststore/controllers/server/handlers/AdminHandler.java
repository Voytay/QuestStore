package com.codecool.Queststore.controllers.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;

public class AdminHandler extends AbstractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (!isUserLogged(httpExchange) || !isAdminUser(httpExchange)) {
            sendRedirectResponse("", httpExchange, "/log");
        }

        JtwigTemplate template = JtwigTemplate.classpathTemplate("static/templates/admin.twig.html");
        JtwigModel model = JtwigModel.newModel().with("username", "Dupa");
        String response = template.render(model);
        sendResponse(response, httpExchange, "/admin/dupa");


    }
}
