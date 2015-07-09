package ua.woodyutilities.frames;

import ua.woodyutilities.controls.MenuBarControl;
import ua.woodyutilities.controls.ResultPanelControl;
import ua.woodyutilities.util.PropertyManager;
import ua.woodyutilities.views.MainMenuBar;
import ua.woodyutilities.views.Material;
import ua.woodyutilities.views.ResultPanel;
import ua.woodyutilities.views.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @autor Alex Iakovenko
 * Date: 6/20/15
 * Time: 8:48 PM
 */
public class MainFrame extends JFrame {

    public static final int FRAME_HEIGHT = 500;
    public static final int FRAME_WIDTH = 900;

    private static final String FRAME_TITLE = "Woody Utilities";
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final PropertyManager PM = PropertyManager.getInstance();

    private static MainFrame instance;

    private MainFrame(){
        super(FRAME_TITLE);
    }
    public static synchronized MainFrame getInstance(){
        if (instance == null){
            instance = new MainFrame();
        }
        return instance;
    }

    public void startFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocation(SCREEN_SIZE.width/2-FRAME_WIDTH/2, SCREEN_SIZE.height/2-FRAME_HEIGHT/2);
        setVisible(true);

        ImageIcon im = new ImageIcon(PM.getValue(PropertyManager.ICO_PATH) + File.separator + PM.getValue(PropertyManager.ICO_MAIN));
        setIconImage(im.getImage());

        BorderLayout layoutManager = new BorderLayout();
        setLayout(layoutManager);
        MainMenuBar mainMenuBar = MainMenuBar.getInstance();
        JMenuBar menuBar = mainMenuBar.create();
        MenuBarControl.register();
        add(menuBar, BorderLayout.NORTH);

        Material material = Material.getInstance();
        JTable materials = material.create();
        JScrollPane scrollMaterials = new JScrollPane(materials);

        add(scrollMaterials, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        ResultPanel resultPanel = ResultPanel.getInstance();
        JPanel buttons = resultPanel.create();
        ResultPanelControl.register();
        bottomPanel.add(buttons, BorderLayout.NORTH);

        StatusBar statusBar = StatusBar.getInstance();
        JPanel statusBarPanel = statusBar.create();
        bottomPanel.add(statusBarPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);




//        setLayout(layoutManager);
    }



}
