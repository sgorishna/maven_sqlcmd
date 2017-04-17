package com.juja.sqlcmd.controller.commands;

import com.juja.sqlcmd.exception.InvalidDataException;
import com.juja.sqlcmd.model.DbManager;
import com.juja.sqlcmd.view.Console;

import java.util.List;

/**
 * Created by Svetik on 03/04/2017.
 */
public class Find implements Process {

    private DbManager dbManager;

    private Console view;

    public Find(DbManager manager, Console view) {
        this.dbManager = manager;
        this.view = view;

    }

    private void print(List<String> tables) {

        int stride = tables.size() / 3;
        for (int row = 0; row < tables.size() / 3; row++) {
            System.out.println(String.format("%20s %20s %12s", tables.get(row),
                    tables.get(row + stride), tables.get(row + stride * 2)));
        }

    }

    @Override
    public void process(String[] command) {

        if (dbManager.getConnection() == null) {

            view.print("Connect to db first!");
        } else if (command.length != 2) {
            view.println("Invalid set of commands, please check your input ");

        } else {

            try {
                List<String> res = dbManager.find(command[1]);

                print(res);

            } catch (InvalidDataException e) {

                view.println(e.getMessage() + ". Please try again");
            }

        }
    }

    @Override
    public boolean canProcess(String cmd) {

        if (cmd.equals("find"))
            return true;
        else
            return false;
    }
}
