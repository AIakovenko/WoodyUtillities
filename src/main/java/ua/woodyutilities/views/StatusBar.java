package ua.woodyutilities.views;

import ua.woodyutilities.views.popups.*;
import ua.woodyutilities.views.popups.PopupMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @autor Alex Iakovenko
 * Date: 6/26/15
 * Time: 8:14 PM
 */
public class StatusBar {
    private static StatusBar instance;
    private JPanel statusPanel;
    private DefaultListModel<String> listModel;
    private JList<String> statuses;
    private DefaultListCellRenderer listCellRenderer;
    private ua.woodyutilities.views.popups.PopupMenu popupMenu;
    private StatusBar(){}

    public static StatusBar getInstance(){
        if (instance == null){
            instance = new StatusBar();
        }
        return instance;
    }

    public JPanel create(){
        statusPanel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<>();
        statuses = new JList<>(listModel);
        listCellRenderer = new DefaultListCellRenderer();
        statuses.setCellRenderer(listCellRenderer);
        JScrollPane scrollPane = new JScrollPane(statuses);
        statusPanel.add(scrollPane, BorderLayout.CENTER);

        popupMenu = new PopupMenu();
        popupMenu.addMenuItem("clear");
        JPopupMenu menu = popupMenu.getMenu();

        statuses.addMouseListener(new PopupMenuAdapter(menu)
//        {
//            @Override
//            public void maybeShowPopup(MouseEvent e) {
//                if (e.isPopupTrigger()) {
//                    menu.show(e.getComponent(),
//                            e.getX(), e.getY());
//                }
//                if (e.getButton() == MouseEvent.BUTTON3) {
//                    System.out.println("some");
////                    Point point = e.getPoint();
////                    int column = mainTable.columnAtPoint(point);
////                    int row = mainTable.rowAtPoint(point);
////                    mainTable.setColumnSelectionInterval(column, column);
////                    mainTable.setRowSelectionInterval(row, row);
////                    selectedRow = mainTable.getSelectedRow();
//                }
//
//            }
//        }
        );


        return statusPanel;
    }

    public void addStatus(String status, boolean state){
        listModel.addElement(status);

//        if (!state){
//            listCellRenderer.
//            Component component =  statuses.getComponent(listModel.lastIndexOf(status));
//
//            component.setForeground(Color.RED);
//        }


    }

    public JPopupMenu getPopupMenu(){
        return popupMenu.getMenu();
    }

    public JMenuItem getMenuItem(String itemName){
        if(popupMenu != null){
            return popupMenu.getMenuItem(itemName);
        }
        return null;
    }





}
