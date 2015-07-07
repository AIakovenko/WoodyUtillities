package ua.woodyutilities.controls;

import ua.woodyutilities.models.Command;
import ua.woodyutilities.models.OperationFactory;
import ua.woodyutilities.views.MainMenuBar;

import javax.swing.*;

/**
 * @autor Alex Iakovenko
 * Date: 6/21/15
 * Time: 9:41 AM
 */
public class MenuBarControl {
    public static MenuBarControl instance;

    private MenuBarControl(){}

    public static synchronized void register(){
        if(instance == null){
            instance = new MenuBarControl();
            instance.registerMenuBarActions();
        }

    }

    private void registerMenuBarActions(){
        MainMenuBar menuBar = MainMenuBar.getInstance();
        JMenuItem importItem = menuBar.getImportItem();
        importItem.addActionListener(e -> {
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
