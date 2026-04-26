package Interfaces;

import Commands.Context;
import Exceptions.WrongCommand;

import java.util.List;

public interface Command
{
    String execute(String[] args, Context context) throws WrongCommand;
    String helpMsg();
}
