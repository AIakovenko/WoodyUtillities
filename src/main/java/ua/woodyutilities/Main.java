package ua.woodyutilities;

import ua.woodyutilities.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @autor Alex Iakovenko
 * Date: 6/20/15
 * Time: 9:13 PM
 */
public class Main {
    private static MainFrame mainFrame;
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
//            logger.info(ex.getMessage());
        } catch (IllegalAccessException ex) {
//            logger.warning(ex.getMessage());
            ex.printStackTrace();
        } catch (InstantiationException ex) {
//            logger.log(Level.SEVERE, ex.getMessage(), ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
//            logger.log(Level.SEVERE, ex.getMessage(), ex);
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event-dispatching thread:
        //adding TrayIcon.
        SwingUtilities.invokeLater(() ->{
                try {
                    mainFrame = MainFrame.getInstance();
                    mainFrame.startFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });
    }
}
