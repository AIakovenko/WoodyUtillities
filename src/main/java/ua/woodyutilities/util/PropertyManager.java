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
