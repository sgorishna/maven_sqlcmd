package com.juja.sqlcmd.model;

import com.juja.sqlcmd.exception.InvalidDataException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Svetik on 19/03/2017.
 */
public class DbManagerImpl implements DbManager {


    private Connection connection;

    @Override
    public Connection getConnection() {

        return connection;
    }

    @Override
    public Connection connectToDb(String db, String user, String pwd) throws InvalidDataException {
        if (connection == null) {

            String dbURL = "dbc:postgresql:" + db + "?user=" + user + "&password=" + pwd;

            try {
                connection = DriverManager.getConnection(dbURL);

            } catch (SQLException e) {


                throw new InvalidDataException(e.getMessage());


            }
        }
        return connection;
    }

    @Override
    public void closeConnection() {

        if (connection != null) {

            try {
                connection.close();
            } catch (SQLException e) {

                //TODO
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public List<String> tables() {

        List<String> tableNames = new ArrayList<>();


        try (Statement statement = connection.createStatement()) {

            try (ResultSet rs = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE'")) {

                while (rs.next()) {

                    tableNames.add(rs.getString("table_name"));

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return tableNames;
    }

    @Override
    public void clear(String tableName) throws InvalidDataException {

        StringBuilder query = new StringBuilder(" delete from public.");
        query.append(tableName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(query))) {

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new InvalidDataException(e.getMessage());
        }


    }

    @Override
    public void drop(String tableName) throws InvalidDataException {

        StringBuilder query = new StringBuilder("DROP TABLE ");
        query.append(tableName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(query))) {

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new InvalidDataException(e.getMessage());
        }


    }

    @Override
    public void create(String[] values) throws InvalidDataException {

        StringBuilder query = new StringBuilder("CREATE TABLE ");

        //query.append(values[1] + "( ID INT PRIMARY KEY NOT NULL");

        query.append(values[1]).append("( ");

        for (int i = 2; i < values.length; i++) {

            if (i == values.length - 1) {

                query.append(values[i]).append(" TEXT ");

            } else

                query.append(values[i]).append(" TEXT, ");
        }

        query.append(")");


        try (PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(query))) {


            preparedStatement.executeUpdate();


        } catch (SQLException e) {

            throw new InvalidDataException(e.getMessage());
        }

    }

    @Override
    public List<String> find(String tableName) throws InvalidDataException {

        List<String> result = new ArrayList<>();
        StringBuilder query = new StringBuilder(" select * from public.");
        query.append(tableName);
        //query.append(" order by id asc");


        try (PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(query))) {

            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            while (rs.next()) {

                result.add("--------");

                for (int i = 1; i <= columnCount; i++)

                    result.add(rs.getString(i));
            }


        } catch (SQLException e) {
            throw new InvalidDataException(e.getMessage());
        }

        return result;
    }

    @Override
    public void insert(String tableName, String[] cols, String[] vals) throws InvalidDataException {

        StringBuilder query = new StringBuilder("INSERT INTO public.");
        query.append(tableName);
        query.append("(");
        for (int i = 0; i < cols.length; i++) {

            if (i == cols.length - 1) {

                query.append(cols[i]);
            } else

                query.append(cols[i]).append(" , ");
        }

        query.append(") VALUES ( ");

        for (int i = 0; i < vals.length; i++) {

            if (i == vals.length - 1) {

                query.append("'").append(vals[i]).append("'");
            } else

                query.append("'").append(vals[i]).append("'").append(" , ");
        }

        query.append(" )");

        try (PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(query))) {

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new InvalidDataException(e.getMessage());
        }


    }

    @Override
    public int delete(String tableName, String col, String value) throws InvalidDataException {

        int deletedRows = 0;

        StringBuilder query = new StringBuilder(" delete from public.");
        query.append(tableName);
        query.append(" where ").append(col).append( " = ").append("'").append(value).append("'");

        try (PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(query))) {

            deletedRows= preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new InvalidDataException(e.getMessage());
        }

return deletedRows;
    }

    @Override
    public int update(String tableName, String idCol, String idVal, String [] сolsToUpdate, String [] valsToUpdate ) throws InvalidDataException {

        int updatedRows = 0;

        StringBuilder query = new StringBuilder(" UPDATE public.");
        query.append(tableName);
        query.append(" SET ");
        for (int i = 0; i < сolsToUpdate.length; i++) {

                query.append(сolsToUpdate[i]).append(" = ");

            if (i == сolsToUpdate.length - 1) {

                query.append("'" + valsToUpdate[i] + "'");

            } else

                query.append("'" + valsToUpdate[i] + "'") .append(",");

        }

        query.append(" WHERE ").append(idCol).append( " = ").append("'").append(idVal).append("'");




        try (PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(query))) {

            updatedRows =  preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new InvalidDataException(e.getMessage());
        }


return updatedRows;

    }
}
