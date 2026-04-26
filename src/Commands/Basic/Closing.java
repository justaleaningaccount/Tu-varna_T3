package Commands.Basic;

import Interfaces.Command;
import Commands.*;

public class Closing implements Command
{

    @Override
    public String execute(String[] args,Context context)
    {

        String file = context.getFilename();
        return "Closing" +file;
    }

    @Override
    public String helpMsg() {
        return "Closes file\n";
    }
}
