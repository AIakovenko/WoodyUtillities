package ua.woodyutilities.data;

import java.io.File;
import java.util.UnknownFormatConversionException;

/**
 * @autor Alex Iakovenko
 * Date: 6/21/15
 * Time: 10:32 AM
 */
public class DataFile {
    private static DataFile instance;
    private File importedFile;

    private DataFile(){}

    public static synchronized DataFile getInstance(){
        if (instance == null){
            instance = new DataFile();
        }
        return instance;
    }

    public void setImportedFile(File newFile){
        importedFile = newFile;
    }

    public File getImportedFile(){
        return importedFile;
    }

    @Override
    public Object clone(){
        throw new UnsupportedOperationException();
    }


}
