package Commands;

import Exceptions.WrongCommand;

import java.util.List;

public interface Command
{
    String execute(List<String> args) throws WrongCommand;
    String helpMsg();
}
