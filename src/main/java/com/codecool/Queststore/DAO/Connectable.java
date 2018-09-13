package com.codecool.Queststore.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connectable {
    public Connection getConnection(){
        Connection con = null;
        try {
            con = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5777/queststore",
                    "postgres", "password");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            System.out.println("Opened database successfully");
        }
        return con;
    }

}
