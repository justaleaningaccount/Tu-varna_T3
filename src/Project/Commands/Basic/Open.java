package Project.Commands.Basic;

import Project.Commands.Context;
import Project.Exceptions.NoElement;
import Project.Interfaces.Command;

public class Open implements Command {
    @Override
    public String execute(String[] args, Context context) throws NoElement
    {
        if (args.length < 1)
        {
            throw new NoElement("Too little arguments");
        }
        String file = args[0];

        return "Opened file" +file;
    }

    @Override
    public String helpMsg()
    {
        return "Opens file, if file doesnt exist, create new one";
    }
}
