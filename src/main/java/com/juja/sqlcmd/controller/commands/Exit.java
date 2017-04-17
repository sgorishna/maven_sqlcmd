package com.juja.sqlcmd.controller.commands;

import com.juja.sqlcmd.view.Console;
import com.juja.sqlcmd.view.ConsoleImpl;

/**
 * Created by Svetik on 17/03/2017.
 */
public class Exit implements Process {

    //private DbManager dbManager = new DbManagerImpl();

    private Console view = new ConsoleImpl();

    @Override
    public void process(String[] values) {

      //  dbManager.closeConnection();

        view.println("Exiting..done!");


    }

    @Override
    public boolean canProcess(String cmd) {

        if(cmd.equals("exit"))
            return true;
        else
            return false;
    }
}
