package ua.woodyutilities.views;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import static org.junit.Assert.*;
/**
 * @autor Alex Iakovenko
 * Date: 7/8/15
 * Time: 11:28 AM
 */
public class MaterialTest {
    private Material instance;

    @Before
    public void setUp() throws Exception {
        instance = Material.getInstance();
    }

    @Test
    public void testGetInstance() throws Exception {
        assertNotNull(instance);
    }

    @Test
    public void testCreate() throws Exception {
        int columnNumber = 2;
        JTable table = instance.create();
        assertNotNull(table);
        if (table != null){
            assertTrue(table.getColumnCount() == columnNumber);
        }


    }

    @Test
    public void testColumnNumber() throws Exception{
        int columnNumber = 2;
        JTable table = instance.create();
        assertTrue(table.getColumnCount() == columnNumber);


    }

    @Test
    public void testAddRow() throws Exception {
        String material = "Test material";
        instance.addRow(material);
        assertTrue(instance.getRowsCount() == 1);

    }

    @Test
    public void testClearTable() throws Exception {
        instance.create();
        if (instance.getRowsCount() < 1){
            String material = "Test material";
            instance.addRow(material);
        }

        int rows;
        instance.clearTable();
        rows = instance.getRowsCount();
        assertTrue(rows == 0);
    }
}
