package Commands;

import Commands.Basic.*;
import Commands.Project.Select;
import Commands.Project.Setting;
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
        commands.put("Exit",new Exiting());
        commands.put("Help",new Help());
        commands.put("Save",new Saving());
        commands.put("Open",new Open());
        commands.put("SavingAs",new SavingAs());
        commands.put("Closing",new Closing());

        commands.put("Select",new Select());
        commands.put("Set",new Setting());
    }
    public Command getCommand(String commandName) {
        Command command = commands.get(commandName.toLowerCase());

        if (command == null)
        {
            throw new WrongCommand("Invalid command. Please  use 'help'  for help");
        }

        return command;
    }
}
