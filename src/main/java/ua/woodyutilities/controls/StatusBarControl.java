package ua.woodyutilities.controls;

import ua.woodyutilities.models.Command;
import ua.woodyutilities.models.OperationFactory;
import ua.woodyutilities.views.MainMenuBar;
import ua.woodyutilities.views.StatusBar;
import ua.woodyutilities.views.popups.PopupMenu;
import ua.woodyutilities.views.popups.PopupMenuAdapter;

import javax.swing.*;

/**
 * @autor Alex Iakovenko
 * Date: 6/30/15
 * Time: 9:40 AM
 */
public class StatusBarControl {
    public static StatusBarControl instance;

    private StatusBarControl(){}

    public static synchronized void register(){
        if(instance == null){
            instance = new StatusBarControl();
            instance.registerStatusBarActions();
        }

    }

    private void registerStatusBarActions(){
        StatusBar statusBar = StatusBar.getInstance();

        JMenuItem clearItem = statusBar.getMenuItem("clear");
        clearItem.addActionListener(e -> {
            OperationFactory factory = OperationFactory.getInstance();
            Command importFile = factory.getCommand(factory.IMPORT_FILE);
            importFile.execute();

        });
    }

    @Override
    public Object clone(){
        throw new UnsupportedOperationException();
    }

}
