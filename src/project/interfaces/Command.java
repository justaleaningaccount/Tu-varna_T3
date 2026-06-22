package project.interfaces;

import project.commands.Context;
import project.exceptions.NoElement;

public interface Command
{
    String execute(String[] args, Context context) throws NoElement;
    String helpMsg();
}
