package com.juja.sqlcmd.controller.commands;

import com.juja.sqlcmd.model.DbManager;
import com.juja.sqlcmd.model.DbManagerImpl;
import com.juja.sqlcmd.view.Console;
import com.juja.sqlcmd.view.ConsoleImpl;

/**
 * Created by Svetik on 17/03/2017.
 */
public class Exit implements Action {

    private DbManager dbManager = new DbManagerImpl();

    private Console view = new ConsoleImpl();

    @Override
    public void action(String[] values) {

        dbManager.closeConnection();

        view.print("Exiting..done!");
        System.exit(0);


    }
}
