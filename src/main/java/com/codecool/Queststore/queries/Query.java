package com.codecool.Queststore.queries;

import com.codecool.Queststore.DAO.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Query {
    protected  Connection con = ConnectionProvider.getConnection();

    public abstract PreparedStatement toPreparedStatement() throws SQLException;
}
