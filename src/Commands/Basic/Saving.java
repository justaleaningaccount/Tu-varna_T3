package Commands.Basic;
import Commands.Context;
import Exceptions.WrongCommand;
import Interfaces.Command;

import java.util.List;

public class Saving implements Command
{

    @Override
    public String execute(String[] args, Context context) throws WrongCommand {
        return "";
    }

    @Override
    public String helpMsg() {
        return "Saves file";
    }
}
