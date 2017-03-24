package com.juja.sqlcmd.model;

import java.sql.Connection;

/**
 * Created by Svetik on 19/03/2017.
 */
public interface DbManager {

    public Connection connectToDb(String db, String user, String pwd);

    public void closeConnection();

}
