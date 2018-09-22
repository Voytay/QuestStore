package com.codecool.Queststore;

import com.codecool.Queststore.controllers.server.Server;

public class App
{
    public static void main( String[] args )
    {
        Server srv = new Server();
        srv.run();
    }
}
