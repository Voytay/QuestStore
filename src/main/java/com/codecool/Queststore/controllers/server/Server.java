package com.codecool.Queststore.controllers.server;

import com.codecool.Queststore.controllers.server.handlers.AdminHandler;
import com.codecool.Queststore.controllers.server.handlers.CreateMenHandler;
import com.codecool.Queststore.controllers.server.handlers.LoginPageHandler;
import com.codecool.Queststore.controllers.server.handlers.Static;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Server {
    public void run() {
        try
        {
            HttpServer server = HttpServer.create(new InetSocketAddress(5777), 0);
            server.createContext("/log", new LoginPageHandler());
            server.createContext("/static", new Static());
            server.createContext("/admin", new AdminHandler());
            server.createContext("/create_mentor", new CreateMenHandler());
            server.createContext("/edit_mentor", new AdminHandler());
            server.createContext("/show_mentors", new AdminHandler());

            server.setExecutor(null);
            server.start();
        } catch (
                Exception e)
        {
            System.out.println("Server start failed!");
            System.out.println(e.getMessage());
        }
    }
}
