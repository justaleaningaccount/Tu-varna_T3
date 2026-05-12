package Project.Interfaces;

import Project.Commands.Context;
import Project.Exceptions.NoElement;

public interface Command
{
    String execute(String[] args, Context context) throws NoElement;
    String helpMsg();
}
