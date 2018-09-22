package com.codecool.Queststore.queries;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AllMentors extends Query {
    private static final String QUERY = "SELECT * FROM person WHERE role = 2";

    public PreparedStatement toPreparedStatement() throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(QUERY);
        con.close();
        return preparedStatement;
    }
}
