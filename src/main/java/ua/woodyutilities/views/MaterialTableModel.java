package ua.woodyutilities.views;

import ua.woodyutilities.util.LocalizationManager;

import javax.swing.table.DefaultTableModel;

/**
 * @autor Alex Iakovenko
 * Date: 6/21/15
 * Time: 2:47 PM
 */
public class MaterialTableModel extends DefaultTableModel {
    private static final LocalizationManager LM = LocalizationManager.getInstance();
//    private static final String[] TITLE = new String[]{
//            " ",
//            LM.getProperty("TABLE_TITLE_MATERIAL_NAME"),
//            LM.getProperty("TABLE_TITLE_CUSTOM_MATERIAL_NAME")};
    private static final String[] TITLE = new String[]{
            " ",
            LM.getProperty("TABLE_TITLE_MATERIAL_NAME")};
    public MaterialTableModel() {
        super(TITLE, 0);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class clazz = String.class;
        switch (columnIndex) {
            case 0:
                clazz = Boolean.class;
                break;
        }
        return clazz;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 0 || column == 2;
    }

}
