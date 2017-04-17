package com.juja.sqlcmd.controller.commands;

import com.juja.sqlcmd.exception.InvalidDataException;
import com.juja.sqlcmd.model.DbManager;
import com.juja.sqlcmd.view.Console;

/**
 * Created by Svetik on 02/04/2017.
 */
public class Clear implements Process {

    private DbManager dbManager;

    private Console view;

    public Clear(DbManager manager, Console view) {
        this.dbManager = manager;
        this.view = view;

    }


    @Override
    public void process(String[] command) {

        if (dbManager.getConnection() == null) {

            view.print("Connect to db first!");
        } else if (command.length != 2) {
            view.println("Invalid set of commands, please check your input ");

        } else {

            try {
                dbManager.clear(command[1]);
                view.println("Table " + command[1] + " is empty now");
            } catch (InvalidDataException e) {

                view.println(e.getMessage() + ". Please try again");
            }

        }
    }

    @Override
    public boolean canProcess(String cmd) {

        if (cmd.equals("clear"))
            return true;
        else
            return false;
    }
}
