package com.juja.sqlcmd.controller.commands;

import com.juja.sqlcmd.exception.InvalidDataException;
import com.juja.sqlcmd.model.DbManager;
import com.juja.sqlcmd.view.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Svetik on 10/04/2017.
 */
public class Update implements Process {

    private DbManager dbManager;

    private Console view;

    public Update(DbManager manager, Console view) {
        this.dbManager = manager;
        this.view = view;

    }

    private String[] getColsToUpdate(String[] command) {

        List<String> temp = new ArrayList<>();

        String[] cols;

        for (int i = 4; i < command.length; i += 2) {

            temp.add(command[i]);

        }

        cols = temp.toArray(new String[temp.size()]);
        return cols;
    }

    private String[] getValsToUpdate(String[] command) {

        List<String> temp = new ArrayList<>();

        String[] vals;

        for (int i = 5; i < command.length; i += 2) {

            temp.add(command[i]);

        }

        vals = temp.toArray(new String[temp.size()]);
        return vals;
    }

    @Override
    public void process(String[] command) {

        int updatedRows;


        String[] colsToUpdate = getColsToUpdate(command);

        String[] valsToUpdate = getValsToUpdate(command);

        if (dbManager.getConnection() == null) {

            view.print("Connect to db first!");
        }
        if (command.length < 6) {
            view.println("Invalid set of commands, please check your input. There must be at least table name, the column and value - identifiers , column and value for an update ");

        } else if (colsToUpdate.length != valsToUpdate.length) {

            view.println("Invalid set of commands, please check your input. No value found for a column ");

        } else {

            try {
                updatedRows = dbManager.update(command[1], command[2], command[3], colsToUpdate, valsToUpdate);
                view.println("Success! " + updatedRows + " rows  was updated");
            } catch (InvalidDataException e) {

                view.println(e.getMessage() + ". Please try again");
            }


        }

    }

    @Override
    public boolean canProcess(String cmd) {

        if (cmd.equals("update"))
            return true;
        else
            return false;
    }
}
