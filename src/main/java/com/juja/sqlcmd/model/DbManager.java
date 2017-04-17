package com.juja.sqlcmd.model;

import com.juja.sqlcmd.exception.InvalidDataException;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Svetik on 19/03/2017.
 */
public interface DbManager {

    public Connection connectToDb(String db, String user, String pwd) throws InvalidDataException;

    public Connection getConnection();

    public void closeConnection();

    public List<String> tables();

    public void clear(String tableName) throws InvalidDataException;

    public void drop(String tableName) throws InvalidDataException;

    public void create(String[] values ) throws InvalidDataException;

    public List<String> find(String tableName) throws InvalidDataException;

    public void insert(String tableName, String[] cols, String[] vals ) throws InvalidDataException;

    public int delete(String tableName, String col, String value ) throws InvalidDataException;

    public int update(String tableName, String idCol, String idVal, String [] сolsToUpdate, String [] valsToUpdate ) throws InvalidDataException;


}
