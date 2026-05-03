package Interfaces;

import Commands.Context;
import Exceptions.WrongCommand;

public interface Command
{
    String execute(String[] args, Context context) throws WrongCommand;
    String helpMsg();
}
