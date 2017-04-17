package com.juja.sqlcmd.controller.commands;

import com.juja.sqlcmd.exception.InvalidDataException;
import com.juja.sqlcmd.model.DbManager;
import com.juja.sqlcmd.view.Console;

/**
 * Created by Svetik on 09/04/2017.
 */
public class Delete implements Process {


    private DbManager dbManager;

    private Console view;

    public Delete(DbManager manager, Console view) {
        this.dbManager = manager;
        this.view = view;

    }


    @Override
    public void process(String[] command) {

        int deleted_rows = 0;

        String tableName = command[1];

        String col = command[2];

        String value = command[3];

        if (dbManager.getConnection() == null) {

            view.print("Connect to db first!");
        } else if (command.length != 4) {
            view.println("Invalid set of commands, please check your input ");

        } else {

            try {
                deleted_rows = dbManager.delete(tableName, col, value);

                view.println("Success! " + deleted_rows + " rows  was deleted");

            } catch (InvalidDataException e) {

                view.println(e.getMessage() + ". Please try again");
            }

        }

    }

    @Override
    public boolean canProcess(String cmd) {
        if (cmd.equals("delete"))
            return true;
        else
            return false;
    }

}

