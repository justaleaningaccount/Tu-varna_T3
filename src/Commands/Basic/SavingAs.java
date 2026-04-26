package Commands.Basic;

import Commands.Context;
import Exceptions.WrongCommand;
import Interfaces.Command;

public class SavingAs implements Command {
    @Override
    public String execute(String[] args, Context context) throws WrongCommand {
        return "";
    }

    @Override
    public String helpMsg() {
        return "Save in specific file";
    }
}
