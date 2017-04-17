package com.juja.sqlcmd.controller.commands;

import com.juja.sqlcmd.model.DbManager;
import com.juja.sqlcmd.view.Console;

import java.util.List;

/**
 * Created by Svetik on 27/03/2017.
 */
public class Tables implements Process {

    private DbManager dbManager;

    private Console view;

    public Tables(DbManager manager, Console view) {
        this.dbManager = manager;
        this.view = view;

    }


    private void print(List<String> tables) {
        view.print("[");

        for (String t : tables) {

            view.print("|" + t + "|");

        }

        view.println("]");
    }

    @Override
    public void process(String[] command) {

        if (dbManager.getConnection() == null) {

            view.print("Connect to db first!");
        } else if(command.length != 1){

            view.println("Incorrect input. Must be only 'tables'");
        }

        else
            print(dbManager.tables());

    }

    @Override
    public boolean canProcess(String cmd) {

        if (cmd.equals("tables"))
            return true;
        else
            return false;
    }


}
