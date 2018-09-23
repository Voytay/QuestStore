package com.codecool.Queststore.queries;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AllStudents extends Query {
    private static final String QUERY = "SELECT * FROM person WHERE role = 3";

    public PreparedStatement toPreparedStatement() throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(QUERY);
        return preparedStatement;
    }
}
