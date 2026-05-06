package Commands.Basic;

import Commands.Context;
import Exceptions.NoElement;
import Exceptions.NoFile;
import Interfaces.Command;

public class SavingAs implements Command {

    public SavingAs() {}

    @Override
    public String execute(String[] args, Context context) throws NoFile
    {
        if (args == null || args.length < 1 || args[0] == null || args[0].isEmpty())
        {
            throw new NoElement("Too little arguments");
        }

        String filename = args[0];
        context.setFilename(filename);

        try
        {
            context.getSaveStorage().saving(context.getElement(), filename);
        }
        catch (Exception e)
        {
            throw new NoFile("Error saving file: " + e.getMessage());
        }
        return "Saved as " + filename;
    }

    @Override
    public String helpMsg()
    {
        return "Save in specific file";
    }
}
