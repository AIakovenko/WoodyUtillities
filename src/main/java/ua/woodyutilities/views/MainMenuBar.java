package ua.woodyutilities.views;

import ua.woodyutilities.util.LocalizationManager;
import ua.woodyutilities.util.PropertyManager;

import javax.swing.*;
import java.io.File;

/**
 * @autor Alex Iakovenko
 * Date: 6/20/15
 * Time: 9:27 PM
 */
public class MainMenuBar{
    public static MainMenuBar instance;
    private static final LocalizationManager LM = LocalizationManager.getInstance();
    private static final PropertyManager PM = PropertyManager.getInstance();
    private static final String ICO_LOCATION = PM.getValue(PropertyManager.ICO_PATH) + File.separator;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem importItem;
    private JMenuItem quitItem;

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
        createQuitItem();
        menuBar.add(fileMenu);
        return menuBar;
    }

    public JMenuItem getImportItem(){
        return importItem;
    }
    public JMenuItem getQuitItem(){
        return quitItem;
    }

    private void createFileItems(){
        importItem = new JMenuItem(LM.getProperty("ITEM_IMPORT"));
        ImageIcon im = new ImageIcon(ICO_LOCATION + PM.getValue(PropertyManager.ICO_IMPORT));
        importItem.setIcon(im);
        fileMenu.add(importItem);

    }

    private void createQuitItem(){
        quitItem = new JMenuItem(LM.getProperty("ITEM_QUIT"));
        ImageIcon im = new ImageIcon(ICO_LOCATION + PM.getValue(PropertyManager.ICO_QUIT));
        quitItem.setIcon(im);
        fileMenu.addSeparator();
        fileMenu.add(quitItem);
    }




}
