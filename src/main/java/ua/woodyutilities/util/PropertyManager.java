package ua.woodyutilities.util;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * @autor Alex Iakovenko
 * Date: 7/6/15
 * Time: 5:35 PM
 */
public class PropertyManager {

    public static final String PATH_EXPORTED_FILES = "PATH_EXPORTED_FILES";
    public static final String ICO_PATH = "ICO_PATH";
    public static final String ICO_MAIN = "ICO_MAIN";
    public static final String ICO_IMPORT = "ICO_IMPORT";
    public static final String ICO_QUIT = "ICO_QUIT";


    private static PropertyManager instance;
    private ResourceBundle bundle;

    private PropertyManager(){
        bundle = ResourceBundle.getBundle("application");
    }

    public static PropertyManager getInstance(){
        if (instance == null){
            instance = new PropertyManager();
        }

        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }






}
