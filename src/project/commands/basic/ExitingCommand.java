package project.commands.basic;

import project.commands.Context;
import project.interfaces.Command;
/**
 * Terminates the running JVM process immediately.
 * Calls System.exit(0) and does not return to the caller.
 * Intended for interactive use to stop the application.
 */

public class ExitingCommand implements Command
{
    public ExitingCommand(){}

    @Override
    public String execute(String[] args, Context context)
    {
        System.exit(0);
        return "Exit";
    }

    @Override
    public String helpMsg() {
        return "Exits program.\n";
    }
}
