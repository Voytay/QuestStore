package com.codecool.Queststore.DAO;

import java.sql.*;

public abstract class  DAO {
    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/queststore",
                    "postgres", "123");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            System.out.println("Opened database successfully");
        }
        return con;
    }
    public ResultSet executeQuery(String query) throws SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();
        statement.execute(query);
        con.close();
        return statement.getResultSet();
    }

    public void insertQuery(PreparedStatement statement){

    }

}