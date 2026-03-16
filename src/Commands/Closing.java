package Commands;

import java.util.List;

public class Closing implements Command
{

    @Override
    public String execute(List<String> args)
    {
        //ToDo
        return "Closing..";
    }

    @Override
    public String helpMsg() {
        return "Closes file\n";
    }
}
