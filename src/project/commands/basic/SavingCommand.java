package project.commands.basic;
import project.commands.Context;
import project.exceptions.NoFile;
import project.interfaces.Command;
/**
 * Saves the current in-memory document to the file referenced by context.
 * Throws NoFile if no filename is set in the context.
 * Delegates actual persistence to the configured SaveStorage implementation.
 */

public class SavingCommand implements Command
{
    public SavingCommand() {}

    @Override
    public String execute(String[] args, Context context) throws NoFile
    {
        String filename = context.getFilename();
        if (filename == null || filename.isEmpty()) {
            throw new NoFile("No file is currently open.");
        }
        if (!filename.toLowerCase().endsWith(".xml")) {
            filename += ".xml";
            context.setFilename(filename);
        }
        try
        {
            context.getSaveStorage().saving(context.getElement(), filename);
        }
        catch (Exception e)
        {
            throw new NoFile("Error saving file: " + e.getMessage());
        }

        return "Saved " + filename;
    }

    @Override
    public String helpMsg() {
        return "Saves file";
    }
}
