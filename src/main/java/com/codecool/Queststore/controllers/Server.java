package com.codecool.Queststore.controllers;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Server {
    public void run() {
        try
        {
            HttpServer server = HttpServer.create(new InetSocketAddress(5777), 0);
            server.createContext("/log", new LoginPageHandler());
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
