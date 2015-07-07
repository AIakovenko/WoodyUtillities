package ua.woodyutilities.util;

import java.util.ResourceBundle;

/**
 * @autor Alex Iakovenko
 * Date: 6/20/15
 * Time: 11:23 PM
 */
public class LocalizationManager {
    private static LocalizationManager instance;
    private static ResourceBundle bundle;

    private LocalizationManager(){

    }

    public static LocalizationManager getInstance(){
        if(instance == null){
            bundle = ResourceBundle.getBundle("interface", new UTF8Control());
            instance = new LocalizationManager();
        }
        return instance;
    }

    public String getProperty(String key){
        return bundle.getString(key);

    }
}
