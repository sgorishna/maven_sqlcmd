package com.juja.sqlcmd.controller.commands;

import com.juja.sqlcmd.exception.InvalidDataException;
import com.juja.sqlcmd.model.DbManager;
import com.juja.sqlcmd.view.Console;

/**
 * Created by Svetik on 17/03/2017.
 */
public class Connect implements Process {

    private Console view;

    private DbManager dbManager;

    public Connect(DbManager manager, Console view) {
        this.dbManager = manager;
        this.view = view;

    }


    @Override
    public void process(String[] values) {

        if (dbManager.getConnection() != null) {

            view.print("Already connected to db!");
        } else if (values.length != 4) {
            view.println("\"Invalid set of commands, please check your input \"");

        } else {


            try {
                dbManager.connectToDb(values[1], values[2], values[3]);
                view.println("You have successfully connected to db. Type next cmd");
            } catch (InvalidDataException e) {

                view.println(e.getMessage() + ". Please try again");

            }
        }

    }

    @Override
    public boolean canProcess(String cmd) {

        if (cmd.equals("connect"))
            return true;
        else
            return false;
    }


}
