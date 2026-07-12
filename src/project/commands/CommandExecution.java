package project.commands;

import project.commands.basic.*;
import project.commands.project.*;
import project.exceptions.WrongCommand;
import project.interfaces.Command;

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
        commands.put("exit", new ExitingCommand());
        commands.put("help", new HelpCommand());
        commands.put("save", new SavingCommand());
        commands.put("open", new OpenCommand());
        commands.put("savingas", new SavingAsCommand());
        commands.put("close", new ClosingCommand());

        commands.put("select", new SelectCommand());
        commands.put("print", new PrintingCommand());
        commands.put("set", new SettingCommand());
        commands.put("delete", new DeletingCommand());
        commands.put("child",new ChildCommand());
        commands.put("children",new ChildrenCommand());
        commands.put("newchild", new NewChildCommand());
        commands.put("new child", new NewChildCommand());
        commands.put("text",new TextCommand());
    }
    public Command getCommand(String commandName) {
        if (commandName == null || commandName.trim().isEmpty())
        {
            throw new WrongCommand("Command cant be null or empty. Use 'help' for help");
        }
        Command command = commands.get(commandName.toLowerCase());

        if (command == null)
        {
            throw new WrongCommand("Wrong command. Use 'help' for help");
        }
        return command;
    }
}
