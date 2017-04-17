package com.juja.sqlcmd.controller;

import com.juja.sqlcmd.controller.commands.*;
import com.juja.sqlcmd.controller.commands.Process;
import com.juja.sqlcmd.model.DbManager;
import com.juja.sqlcmd.view.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Svetik on 17/03/2017.
 */
public class Executor {


    // private HashMap<String, Process> hmap = new HashMap<>();

    private List<Process> cmds = new ArrayList<>();

    private Console view;

    private DbManager manager;


    // private String[] commands = {"tables", "exit", "clear", "drop", "create", "find", "insert", "delete", "update", "help"};


    public Executor(Console view, DbManager manager) {

        this.view = view;
        this.manager = manager;
        this.initCmds();


    }


    private void initCmds() {

        cmds.add(new Connect(manager, view));

        cmds.add(new Tables(manager, view));

        cmds.add(new Exit());

        cmds.add(new Clear(manager, view));

        cmds.add(new Drop(manager, view));

        cmds.add(new Create(manager, view));

        cmds.add(new Find(manager, view));

        cmds.add(new Insert(manager, view));

        cmds.add(new Delete(manager, view));

        cmds.add(new Update(manager, view));

        cmds.add(new Help(manager, view));

/*
        hmap.put("exit", new Exit());

        hmap.put("tables", new Tables(manager, view));

        hmap.put("clear", new Clear(manager, view));

        hmap.put("drop", new Drop(manager, view));

        hmap.put("create", new Create(manager, view));

        hmap.put("find", new Find(manager, view));

        hmap.put("insert", new Insert(manager, view));

        hmap.put("delete", new Delete(manager, view));

        hmap.put("update", new Update(manager, view));

        hmap.put("help", new Help(manager, view));

        */
    }

    //new
    private void processCmd(String[] values) {

        boolean canProcess = false;


        for (Process a : cmds) {
            canProcess = a.canProcess(values[0]);
            if (canProcess) {
                a.process(values);
                break;

            }
        }

        if (!canProcess)
            view.println("Cant find command: '" + values[0] + "', please try again ");

    }


    /*
    private void runCmds(String[] values) {
        Process a = null;


        for (String command : commands) {

            if (command.equals(values[0])) {

                a = hmap.get(values[0]);

                a.process(values);
            }

        }

        if (a == null) {

            view.println("Cant find command: '" + values[0] + "', please try again ");
        }


    }

    */

    public void run() {

        view.println("Hello, to connect to a db, please enter a db name, username and a password in a format: 'connect|database|username|password'. Or input 'exit' to finish");


        while (true) {

            String input = view.read();

            String[] cmdSet = input.split("\\|");

            if (cmdSet[0].equalsIgnoreCase("exit")) {

                break;
            } else {


                processCmd(cmdSet);

            }


        }

    }

    //new
    public boolean isConnectedToDb() {

        boolean res = false;
        view.println("Hello, to connect to a db, please enter a db name, username and a password in a format: 'connect|database|username|password'. Or input 'exit' to finish");


        while (manager.getConnection() == null) {
            String input = view.read();

            String[] cmds = input.split("\\|");

            if (cmds[0].equalsIgnoreCase("exit")) {

                res = false;

                break;
            } else {


                new Connect(manager, view).process(cmds);


                res = true;

            }
        }
        return res;

    }

    //new
    private void readAndProcessCmds() {

        while (true) {

            String input = view.read();

            String[] cmdSet = input.split("\\|");

            if (cmdSet[0].equalsIgnoreCase("exit")) {

                break;
            } else {


                processCmd(cmdSet);

            }


        }

    }
/*
    public void run() {


        boolean isConnectedToDb = isConnectedToDb();

        if (isConnectedToDb == true) {

            view.println("You have successfully connected to db. Type next cmd");

            readAndProcessCmds();

        }
    }
*/

    /*
    public void run() {

        view.println("Hello, to connect to a db, please enter a db name, username and a password in a format: 'connect|database|username|password'. Or input 'exit' to finish");


        while (manager.getConnection() == null) {
            String input = view.read();

            String[] cmds = input.split("\\|");

            if (cmds[0].equalsIgnoreCase("exit")) {

                break;
            }


            new Connect(manager, view).process(cmds);


        }

        view.println("You have successfully connected to db. Type next cmd");

        while (true) {

            String input = view.read();

            String[] cmds = input.split("\\|");

            if (cmds[0].equalsIgnoreCase("exit")) {

                break;
            }

            this.runCmds(cmds);

        }

    }

    */
}



