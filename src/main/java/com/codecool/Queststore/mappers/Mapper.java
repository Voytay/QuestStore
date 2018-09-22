package com.codecool.Queststore.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<E> {
    E map(ResultSet resultSet) throws SQLException;
}
