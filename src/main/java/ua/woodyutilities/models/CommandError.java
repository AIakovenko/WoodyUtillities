package ua.woodyutilities.models;

/**
 * @autor Alex Iakovenko
 * Date: 6/21/15
 * Time: 10:08 AM
 */
public class CommandError implements Command {
    @Override
    public void execute(){
        System.out.println("Error");
    }
}
