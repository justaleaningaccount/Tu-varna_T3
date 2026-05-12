package Project.Commands.Basic;
import Project.Commands.Context;
import Project.Exceptions.NoFile;
import Project.Interfaces.Command;

public class Saving implements Command
{
    public Saving() {}

    @Override
    public String execute(String[] args, Context context) throws NoFile
    {
        String filename = context.getFilename();
        if (filename == null || filename.isEmpty())
        {
            throw new NoFile("No file is currently open.");
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
