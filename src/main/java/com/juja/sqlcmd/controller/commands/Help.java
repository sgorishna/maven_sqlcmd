package com.juja.sqlcmd.controller.commands;

import com.juja.sqlcmd.model.DbManager;
import com.juja.sqlcmd.view.Console;

/**
 * Created by Svetik on 11/04/2017.
 */
public class Help implements Process {

    private DbManager dbManager;

    private Console view;

    public Help(DbManager manager, Console view) {
        this.dbManager = manager;
        this.view = view;

    }

    @Override
    public void process(String[] command) {

        view.println("");

    }

    @Override
    public boolean canProcess(String cmd) {

        if(cmd.equals("help"))
            return true;
        else
            return false;
    }
}
