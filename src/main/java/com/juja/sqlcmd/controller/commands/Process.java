package com.juja.sqlcmd.controller.commands;

/**
 * Created by Svetik on 17/03/2017.
 */
public interface Process {

    void process(String [] command);

    boolean canProcess(String cmd);


}
