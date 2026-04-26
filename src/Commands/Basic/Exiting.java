package Commands.Basic;

import Commands.Context;
import Interfaces.Command;

import java.util.List;

public class Exiting implements Command
{

    public Exiting(){};

    @Override
    public String execute(String[] args, Context context)
    {
        System.exit(0);
        return "Exit";
    }

    @Override
    public String helpMsg() {
        return "Exits program.\n";
    }
}
