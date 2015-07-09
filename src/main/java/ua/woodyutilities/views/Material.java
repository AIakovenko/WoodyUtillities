package ua.woodyutilities.views;

import ua.woodyutilities.frames.MainFrame;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.*;

/**
 * @autor Alex Iakovenko
 * Date: 6/21/15
 * Time: 1:37 PM
 */
public class Material {
    private static Material instance;
    private JTable materials;
    private MaterialTableModel materialTableModel;
    private Material(){
        materials = new JTable();
        materials.setSize(MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT);
    }

    public static synchronized Material getInstance(){
        if (instance == null){
            instance = new Material();
        }
        return instance;
    }

    public JTable create(){
        final int MATERIAL_FIELD_SIZE = (MainFrame.FRAME_WIDTH) - 52;
//        final int CUSTOM_MATERIAL_FIELD_SIZE = (MATERIAL_FIELD_SIZE-52);

        materialTableModel = new MaterialTableModel();

        materials = new JTable(materialTableModel);
        materials.setRowHeight(20);
        materials.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        materials.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        materials.getColumnModel().getColumn(0).setPreferredWidth(40);
        materials.getColumnModel().getColumn(1).setPreferredWidth(MATERIAL_FIELD_SIZE);
//        materials.getColumnModel().getColumn(2).setPreferredWidth(CUSTOM_MATERIAL_FIELD_SIZE);

        return materials;
    }

    public void addRow(String materialName){
        materialTableModel.addRow(new Object[]{false, materialName, materialName});
    }

    public int getRowsCount(){
        return materials.getRowCount();
    }

    public java.util.List<String> getSelectedMaterials(){
        java.util.List<String> list = new ArrayList<>();
        TableModel model = materials.getModel();
        for (int i = 0; i < model.getRowCount()  ; i++){
            if ((Boolean)model.getValueAt(i,0)){
                list.add((String)model.getValueAt(i,1));
            }
        }
        return list;
    }

    public void clearTable(){
        int count = materials.getRowCount();
        for (int i = count-1; i >= 0; i--){
            materialTableModel.removeRow(i);
        }
    }

}
