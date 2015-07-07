package ua.woodyutilities.views;

import ua.woodyutilities.util.LocalizationManager;

import javax.swing.*;

/**
 * @autor Alex Iakovenko
 * Date: 6/20/15
 * Time: 9:27 PM
 */
public class MainMenuBar{
    public static MainMenuBar instance;
    private final LocalizationManager LM = LocalizationManager.getInstance();

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem importItem;

    private MainMenuBar(){
    }
    public static MainMenuBar getInstance(){
        if(instance == null){
            instance = new MainMenuBar();
        }
        return instance;
    }

    public JMenuBar create(){
        menuBar = new JMenuBar();

        fileMenu = new JMenu(LM.getProperty("ITEM_FILE"));
        createFileItems();
        menuBar.add(fileMenu);
        return menuBar;
    }

    public JMenuItem getImportItem(){
        return importItem;
    }

    private void createFileItems(){
        importItem = new JMenuItem(LM.getProperty("ITEM_IMPORT"));
        fileMenu.add(importItem);

    }


}
