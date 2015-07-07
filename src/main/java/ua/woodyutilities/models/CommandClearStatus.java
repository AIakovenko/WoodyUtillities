package ua.woodyutilities.models;

import ua.woodyutilities.views.StatusBar;

import javax.swing.*;

/**
 * @autor Alex Iakovenko
 * Date: 6/30/15
 * Time: 9:49 AM
 */
public class CommandClearStatus implements Command {

    @Override
    public void execute() {
        StatusBar statusBar = StatusBar.getInstance();
        System.out.println("clear");

    }
}
