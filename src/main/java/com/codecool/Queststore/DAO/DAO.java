package com.codecool.Queststore.DAO;

import com.codecool.Queststore.mappers.Mapper;
import com.codecool.Queststore.queries.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class  DAO<E> {
    Connection con = ConnectionProvider.getConnection();
    protected Mapper<E> mapper;

    public List<E> getRecordsList(Query query) throws SQLException {
        List<E> records = new ArrayList<>();
        PreparedStatement prepStatement = query.toPreparedStatement();
        ResultSet rs = prepStatement.getResultSet();
        while (rs.next()) {
            E recordToAdd = mapper.map(rs);
            records.add(recordToAdd);
        }
        return records;
    }

    public abstract void insertRecord(E record)throws SQLException;

    public abstract void deleteRecord(E record) throws SQLException;

    public abstract void updateRecord(E record) throws SQLException;
}