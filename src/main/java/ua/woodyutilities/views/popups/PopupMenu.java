package ua.woodyutilities.views.popups;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @autor Alex Iakovenko
 * Date: 6/30/15
 * Time: 9:16 AM
 */
public class PopupMenu {
    private JPopupMenu menu;
    private Map<String, JMenuItem> menuItems;

    public PopupMenu(){
        menu = new JPopupMenu();
        menuItems = new HashMap<>();
    }


    public JPopupMenu getMenu(){
        return menu;
    }

    public void addMenuItem(String name){
        JMenuItem item = new JMenuItem(name);
         menuItems.put(name, item);
    }

    public JMenuItem getMenuItem(String name){

        JMenuItem item = null;
        if (menuItems != null){
            item = menuItems.get(name);
        }

        return item;
    }

}
