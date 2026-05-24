package Project.Commands.Basic;

import Project.Commands.Context;
import Project.Exceptions.NoElement;
import Project.Exceptions.NoFile;
import Project.Interfaces.Command;
/**
 * Saves the current document under a new filename and updates context.
 * Validates the provided filename argument and delegates to SaveStorage.
 * Returns a confirmation message indicating the new filename.
 */

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
        if (!filename.toLowerCase().endsWith(".xml")) {
            filename += ".xml";
        }
        context.setFilename(filename);

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
