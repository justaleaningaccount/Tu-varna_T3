package Project.Commands;

import Project.Commands.Basic.*;
import Project.Commands.Project.*;
import Project.Exceptions.WrongCommand;
import Project.Interfaces.Command;

import java.util.HashMap;
import java.util.Map;
/**
 * Registry and factory for available command implementations.
 * Maps command names to Command instances and resolves requests.
 * Throws WrongCommand for unknown or null command names.
 */

public class CommandExecution
{
    private final Map<String, Command> commands;
    public CommandExecution()
    {
        this.commands = new HashMap<>();
        commands.put("exiting", new Exiting());
        commands.put("help", new Help());
        commands.put("saving", new Saving());
        commands.put("open", new Open());
        commands.put("savingAs", new SavingAs());
        commands.put("closing", new Closing());

        commands.put("select", new Select());
        commands.put("printing", new Printing());
        commands.put("setting", new Setting());
        commands.put("deleting", new Deleting());
        commands.put("child",new Child());
        commands.put("children",new Children());
        commands.put("new child",new NewChild());
        commands.put("text",new Text());
    }
    public Command getCommand(String commandName) {
        Command command = commands.get(commandName.toLowerCase());

        if (command == null)
        {
            throw new WrongCommand("Wrong command. Use 'help' for help");
        }
        return command;
    }
}
