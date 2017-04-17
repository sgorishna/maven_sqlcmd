package com.juja.sqlcmd.controller.commands;

import com.juja.sqlcmd.exception.InvalidDataException;
import com.juja.sqlcmd.model.DbManager;
import com.juja.sqlcmd.view.Console;

/**
 * Created by Svetik on 02/04/2017.
 */
public class Create implements Process {

    private DbManager dbManager;

    private Console view;

    public Create(DbManager manager, Console view) {
        this.dbManager = manager;
        this.view = view;

    }

    @Override
    public void process(String[] command) {

        if (dbManager.getConnection() == null) {

            view.print("Connect to db first!");
        } else if (command.length < 3) {
            view.println("Invalid set of commands, please check your input. There must be at least table name and the column name ");

        } else {

            try {
                dbManager.create(command);
                view.println("Table " + command[1] + " has been created");
            } catch (InvalidDataException e) {

                view.println(e.getMessage() + ". Please try again");
            }
        }

    }

    @Override
    public boolean canProcess(String cmd) {

        if (cmd.equals("create"))
            return true;
        else
            return false;
    }

}

