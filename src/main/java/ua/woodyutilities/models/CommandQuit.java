package ua.woodyutilities.models;

/**
 * Describes leaving application operation.
 *
 * @autor Alex Iakovenko
 * Date: 7/8/15
 * Time: 11:02 AM
 */
public class CommandQuit implements Command {

    @Override
    public void execute() {
        System.exit(0);
    }
}
