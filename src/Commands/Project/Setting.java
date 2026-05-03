package Commands.Project;
import Commands.Context;
import Exceptions.WrongCommand;
import Interfaces.Command;

public class Setting implements Command
{

    @Override
    public String execute(String[] args, Context context) throws WrongCommand {
        return "";
    }

    @Override
    public String helpMsg() {
        return "Set a value to an attribute";
    }
}
