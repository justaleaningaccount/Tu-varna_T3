package project.commands.basic;

import project.commands.Context;
import project.exceptions.NoElement;
import project.exceptions.NoFile;
import project.interfaces.Command;
import java.io.File;

/**
 * Saves the current document under a new filename and updates context.
 * If the file already exists, it overwrites it.
 * Validates the provided filename argument and delegates to SaveStorage.
 * Returns a confirmation message indicating the new filename.
 */
public class SavingAsCommand implements Command {

    public SavingAsCommand() {}

    @Override
    public String execute(String[] args, Context context) throws NoFile {
        if (args == null || args.length < 1 || args[0] == null || args[0].isEmpty()) {
            throw new NoElement("Too little arguments");
        }

        String filename = args[0];
        if (!filename.toLowerCase().endsWith(".xml")) {
            filename += ".xml";
        }

        String oldFilename = context.getFilename();
        File oldFile = oldFilename != null ? new File(oldFilename) : null;
        File newFile = new File(filename);

        if (oldFile != null && oldFile.exists() && !oldFile.getName().equals(newFile.getName())) {
            oldFile.renameTo(newFile);
        }

        context.setFilename(filename);

        try {
            context.getSaveStorage().saving(context.getElement(), filename);
            return "Saving as " + filename;
        } catch (Exception e) {
            throw new NoFile("Error saving file: " + e.getMessage());
        }
    }

    @Override
    public String helpMsg() {
        return "Save as specific file.";
    }
}
