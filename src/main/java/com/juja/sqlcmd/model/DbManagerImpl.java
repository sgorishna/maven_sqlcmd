package com.juja.sqlcmd.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Svetik on 19/03/2017.
 */
public class DbManagerImpl implements DbManager{

   private Connection connection ;

    private String dbURL;

    @Override
    public Connection connectToDb(String db, String user, String pwd) {
        if(connection ==null) {

            dbURL = "dbc:postgresql:" + db + "?user=" + user + "&password=" + pwd;

            try {
                connection = DriverManager.getConnection(dbURL);

            } catch (SQLException e) {

                e.printStackTrace();
                // final String message = e.getMessage();
                //System.out.println(message.toString());
            }
        }
        return connection;
    }

    @Override
    public void closeConnection() {

        if(connection !=null){

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
