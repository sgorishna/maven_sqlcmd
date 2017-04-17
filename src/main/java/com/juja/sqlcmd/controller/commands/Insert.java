package com.juja.sqlcmd.controller.commands;

import com.juja.sqlcmd.exception.InvalidDataException;
import com.juja.sqlcmd.model.DbManager;
import com.juja.sqlcmd.view.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Svetik on 04/04/2017.
 */
public class Insert implements Process {

    private DbManager dbManager;

    private Console view;

    public Insert(DbManager manager, Console view) {
        this.dbManager = manager;
        this.view = view;

    }


    private String[] getCols(String[] command) {

        List<String> temp = new ArrayList<>();

        String[] cols;

        for (int i = 2; i < command.length; i += 2) {

            temp.add(command[i]);

        }

        cols = temp.toArray(new String[temp.size()]);
        return cols;
    }

    private String[] getVals(String[] command) {

        List<String> temp = new ArrayList<>();

        String[] vals;

        for (int i = 3; i < command.length; i += 2) {

            temp.add(command[i]);

        }

        vals = temp.toArray(new String[temp.size()]);
        return vals;

    }


    @Override
    public void process(String[] command) {

        String[] cols = getCols(command);

        String[] vals = getVals(command);

        if (dbManager.getConnection() == null) {

            view.print("Connect to db first!");
        }
        if (command.length < 4) {
            view.println("Invalid set of commands, please check your input. There must be at least table name and the column name and value ");

        } else if (cols.length != vals.length) {

            view.println("Invalid set of commands, please check your input. No value found for a column ");

        } else {

            try {
                dbManager.insert(command[1], cols, vals);
                view.println("Success insert");
            } catch (InvalidDataException e) {

                view.println(e.getMessage() + ". Please try again");
            }


        }

    }

    @Override
    public boolean canProcess(String cmd) {

        if (cmd.equals("insert"))
            return true;
        else
            return false;
    }
}
