package com.juja.sqlcmd.controller.commands;

import com.juja.sqlcmd.model.DbManager;
import com.juja.sqlcmd.model.DbManagerImpl;
import com.juja.sqlcmd.view.Console;
import com.juja.sqlcmd.view.ConsoleImpl;

import static com.juja.sqlcmd.model.old.Utils.parseLine;

/**
 * Created by Svetik on 17/03/2017.
 */
public class Connect implements Action {

private DbManager dbManager = new DbManagerImpl();

private Console view = new ConsoleImpl();


    private String dbName;

    private String userName;

    private String password;


    public Connect(String dbName, String userName, String password) {

        this.dbName = dbName;
        this.userName = userName;
        this.password = password;

    }

    private String validateDbName(String db) {

        String dbname;
        dbname = db;

        if (!db.equals(dbName)) {

            view.print("Invalid db name, please enter correct db name: ");

            dbname = view.read();

            validateDbName(dbname);

        }

        return dbname;
    }

    private String validateUserName(String username) {

        String un = username;

        if (!username.equals(userName)) {

            view.print("Invalid user name, please enter correct user name: ");

            un = view.read();

            validateUserName(un);

        }


        return un;
    }


    private String validatePassword(String pwd) {

        String pw = pwd;

        if (!pwd.equals(password)) {

            view.print("Invalid password, please enter correct password: ");

            pw = view.read();

            validatePassword(pw);

        }

        return pw;
    }

    private String[] validateCmds(String[] cmds) {

        String[] set = cmds;

        if (set.length != 4) {

            view.print("Invalid set of commands, please check your input ");

            String line = view.read();

            set = parseLine(line);

            validateCmds(set);

        }


        return set;
    }


    @Override
    public void action(String[] values) {

        String[] cmds = validateCmds(values);

        String dbName = validateDbName(cmds[1]);

        String username = validateUserName(cmds[2]);

        String password = validatePassword(cmds[3]);

        if (dbManager.connectToDb(dbName, username, password) != null) {

            view.print("You have successfully connected to db " + dbName);
        }


    }


}
