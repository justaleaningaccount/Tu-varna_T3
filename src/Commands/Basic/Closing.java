package Commands.Basic;

import Commands.Context;
import Exceptions.NoFile;
import Interfaces.Command;

public class Closing implements Command
{
    public Closing() {}

    @Override
    public String execute(String[] args, Context context) throws NoFile
    {
        String file = context.getFilename();
        if (file == null || file.isEmpty()) {
            throw new NoFile("No file is currently open.");
        }

        context.setFilename(null);
        context.setElement(null);

        return "Closed " + file;
    }

    @Override
    public String helpMsg() {
        return "Closes file\n";
    }
}
