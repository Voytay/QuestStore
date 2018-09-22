package com.codecool.Queststore.queries;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AllArtifacts extends Query {
    private static final String QUERY = "SELECT * FROM artifact";

    public PreparedStatement toPreparedStatement() throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(QUERY);
        con.close();
        return preparedStatement;
    }
}
