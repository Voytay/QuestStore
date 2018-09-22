package com.codecool.Queststore.queries;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AllQuests extends Query {
    private static final String QUERY = "SELECT * FROM quest";

    public PreparedStatement toPreparedStatement() throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(QUERY);
        con.close();
        return preparedStatement;
    }
}
