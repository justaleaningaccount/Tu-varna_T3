package project.commands.basic;

import project.commands.Context;
import project.exceptions.NoFile;
import project.interfaces.Command;
/**
 * Closes the currently open file and clears the editor context.
 * Throws NoFile when no file is open.
 * Returns a confirmation message including the closed filename.
 */

public class ClosingCommand implements Command
{
    public ClosingCommand() {}

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
    public  String helpMsg() {
        return "Closes file\n";
    }
}
