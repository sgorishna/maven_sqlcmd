package com.juja.sqlcmd.controller;

import com.juja.sqlcmd.view.ConsoleImpl;

/**
 * Created by Svetik on 19/03/2017.
 */
public class Main {

    public static void main(String[] args) {

        Executor executor =  new Executor(new ConsoleImpl());

        executor.run();

    }
}
