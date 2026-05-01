package Commands.Project;

import Commands.Context;
import Exceptions.WrongCommand;
import Interfaces.Command;
import org.w3c.dom.*;

import java.util.List;

public class Select implements Command
{

    @Override
    public String execute(String[] args, Context context) throws WrongCommand {
        return "";
    }

    @Override
    public String helpMsg() {
        return "Извежда стойност на атрибут по даден идентификатор на елемента и ключ на атрибута";
    }
}
