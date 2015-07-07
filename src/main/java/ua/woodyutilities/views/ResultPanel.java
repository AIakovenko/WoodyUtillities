package ua.woodyutilities.views;

import ua.woodyutilities.util.LocalizationManager;

import javax.swing.*;
import java.awt.*;

/**
 * @autor Alex Iakovenko
 * Date: 6/21/15
 * Time: 3:56 PM
 */
public class ResultPanel {
    private static final LocalizationManager LM = LocalizationManager.getInstance();
    private static ResultPanel instance;
    private JButton buttonGenerate;
    private JPanel buttonPanel;
    private ResultPanel(){

    }

    public static synchronized ResultPanel getInstance(){
        if (instance == null){
            instance = new ResultPanel();
        }
        return instance;
    }

    public JPanel create(){
        buttonPanel = new JPanel(new FlowLayout());
        buttonGenerate = new JButton(LM.getProperty("BUTTON_GENERATE"));

        buttonPanel.add(buttonGenerate);
        return buttonPanel;
    }

    public JButton getButtonGenerate(){
        return buttonGenerate;
    }


    @Override
    public Object clone(){
        throw new UnsupportedOperationException();
    }



}
