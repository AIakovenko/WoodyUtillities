package ua.woodyutilities.views.popups;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @autor Alex Iakovenko
 * Date: 6/30/15
 * Time: 9:38 AM
 */
public class PopupMenuAdapter extends MouseAdapter {
    private JPopupMenu menu;

    public PopupMenuAdapter(JPopupMenu menu){
        this.menu = menu;
    }
    public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }

    public void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            menu.show(e.getComponent(),
                    e.getX(), e.getY());
        }
    }
}
