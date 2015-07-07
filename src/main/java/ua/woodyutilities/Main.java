package ua.woodyutilities;

import ua.woodyutilities.frames.MainFrame;

import java.awt.*;

/**
 * @autor Alex Iakovenko
 * Date: 6/20/15
 * Time: 9:13 PM
 */
public class Main {
    private static MainFrame mainFrame;
    public static void main(String[] args){
        EventQueue.invokeLater(() ->{
                try {
                    mainFrame = MainFrame.getInstance();
                    mainFrame.startFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });
    }
}
