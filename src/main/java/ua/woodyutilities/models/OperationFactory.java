package ua.woodyutilities.models;

import java.util.HashMap;

/**
 * @autor Alex Iakovenko
 * Date: 6/21/15
 * Time: 9:52 AM
 */
public class OperationFactory {
    public static final String IMPORT_FILE = "import_file";
    public static final String GENERATE = "generate";
    public static final String CLEAR_STATUS = "clear_status";
    public static final String QUIT_APPLICATION = "quit_application";

    private static OperationFactory instance;
    private HashMap<String, Command> commandsMap;
    private OperationFactory(){
        commandsMap = new HashMap<>();
        commandsMap.put(IMPORT_FILE, new CommandImportFile());
        commandsMap.put(GENERATE, new CommandGenerate());
        commandsMap.put(CLEAR_STATUS, new CommandClearStatus());
        commandsMap.put(QUIT_APPLICATION, new CommandQuit());
    }

    public static synchronized OperationFactory getInstance(){
        if (instance == null){
            instance = new OperationFactory();
        }
        return instance;
    }

    public Command getCommand(String action){
        Command command = commandsMap.get(action);
        if (command == null){
            command = new CommandError();
        }
        return command;
    }
    @Override
    public Object clone(){
        throw new UnsupportedOperationException();
    }
}
