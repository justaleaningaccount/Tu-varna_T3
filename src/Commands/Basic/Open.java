package Commands.Basic;

import Commands.Context;
import Exceptions.WrongCommand;
import Interfaces.Command;

import java.util.List;

public class Open implements Command {
    @Override
    public String execute(String[] args, Context context) throws WrongCommand
    {
        if (args.length < 1){
            throw new WrongCommand("Error. Not enough arguments. Usage: open <file>");
        }
        String file = args[0];

        return "Opened file" +file;
    }

    @Override
    public String helpMsg() {
        return "Opens file, if file doesnt exit create new one";
    }
}
