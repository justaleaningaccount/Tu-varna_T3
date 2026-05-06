package Commands;

import Commands.Basic.*;
import Commands.Project.*;
import Exceptions.WrongCommand;
import Interfaces.Command;

import java.util.HashMap;
import java.util.Map;

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
    }
    public Command getCommand(String commandName) {
        if (commandName == null) throw new WrongCommand("Wrong command. Use 'help' for help");
        Command command = commands.get(commandName.toLowerCase());

        if (command == null)
        {
            throw new WrongCommand("Wrong command. Use 'help' for help");
        }
        return command;
    }
}
