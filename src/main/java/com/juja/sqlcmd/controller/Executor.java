package com.juja.sqlcmd.controller;

import com.juja.sqlcmd.controller.commands.Action;
import com.juja.sqlcmd.controller.commands.Connect;
import com.juja.sqlcmd.controller.commands.Exit;
import com.juja.sqlcmd.view.Console;

import java.util.HashMap;

/**
 * Created by Svetik on 17/03/2017.
 */
public class Executor {

    private Console view ;

    private String [] commands = { "connect", "tables", "exit"};


    public Executor(Console view) {

        this.view = view;
        this.init();

    }

    private HashMap<String, Action> hmap = new HashMap<String, Action>();

    private void init() {

        hmap.put("connect", new Connect("sqlcmd","postgres","siruba"));

        hmap.put("exit", new Exit());


    }


    private void runCmds(String [] values){
        Action a = null;

        for (String command : commands) {

            if (command.equals(values[0])) {

                a = hmap.get(values[0]);

                a.action(values);
            }

        }

        if(a == null){

            view.print("Cant find command: " + values[0]+", please try again ");
        }
    }


    public  void run() {


        view.print("Hello, to connect to a db, please enter a db name, username and a password in a format: 'connect|database|username|password'. Or input 'exit' to finish");

        while (true) {

            String input =  view.read();

            runCmds(input.split("\\|"));
        }
    }


}
