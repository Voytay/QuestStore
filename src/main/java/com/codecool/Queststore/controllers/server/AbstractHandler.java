package com.codecool.Queststore.controllers.server;


import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpCookie;

public abstract class AbstractHandler {

    public static void sendResponse(String response, HttpExchange httpExchange) {
        OutputStream os = httpExchange.getResponseBody();

        try {
            byte[] bs = response.getBytes();
            httpExchange.sendResponseHeaders(200, bs.length);
            os.write(bs);
            os.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void sendRedirectResponse(String response, HttpExchange httpExchange){
        OutputStream os = httpExchange.getResponseBody();
        try{
            byte[] bs = response.getBytes();
            httpExchange.sendResponseHeaders(302, bs.length);
            os.write(bs);
            os.close();
        }catch(IOException e){
            e.getMessage();
        }
    }

}
