package ua.woodyutilities.controls;

import ua.woodyutilities.models.Command;
import ua.woodyutilities.models.OperationFactory;
import ua.woodyutilities.views.ResultPanel;

import javax.swing.*;

/**
 * @autor Alex Iakovenko
 * Date: 6/21/15
 * Time: 4:20 PM
 */
public class ResultPanelControl {
    private static ResultPanelControl instance;
    private ResultPanelControl(){}

    public static synchronized ResultPanelControl register(){
        if (instance == null){
            instance = new ResultPanelControl();
            instance.registerButtonsActions();
        }
        return instance;
    }

    private void registerButtonsActions(){
        ResultPanel resultPanel = ResultPanel.getInstance();
        JButton buttonGenerate = resultPanel.getButtonGenerate();
        buttonGenerate.addActionListener(e -> {
            OperationFactory factory = OperationFactory.getInstance();
            Command generation = factory.getCommand(factory.GENERATE);
            generation.execute();
        });
    }

    @Override
    public Object clone(){
        throw new UnsupportedOperationException();
    }
}
