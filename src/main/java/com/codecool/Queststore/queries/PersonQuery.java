package com.codecool.Queststore.queries;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonQuery extends Query {
    private String query;
    public PersonQuery(String string){
        query = "SELECT * FROM person WHERE username = "+ string;
    }
    public PreparedStatement toPreparedStatement() throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(query);
        return preparedStatement;
    }
}
