package com.juja.sqlcmd.view;

import java.util.Scanner;

/**
 * Created by Svetik on 19/03/2017.
 */
public class ConsoleImpl implements Console {




    @Override
    public String read() {

        Scanner input = new Scanner(System.in);

        return input.nextLine();
    }

    @Override
    public void println(String line) {

        System.out.println(line);
    }

    @Override
    public void print(String line) {

        System.out.print(line);
    }
}
