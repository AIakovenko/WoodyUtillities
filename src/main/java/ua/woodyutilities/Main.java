package ua.woodyutilities;

import org.apache.log4j.Logger;
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
    private static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args){
        try {
            logger.info("Application has been run");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            logger.info(ex.getMessage());
        } catch (IllegalAccessException ex) {
            logger.warn(ex.getMessage());
        } catch (InstantiationException ex) {
            logger.warn(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            logger.warn(ex.getMessage());
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
                    logger.error(e.getMessage(), e);
                }
        });
    }
}
