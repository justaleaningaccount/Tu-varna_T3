package Commands.Basic;
import Commands.Context;
import Exceptions.WrongCommand;
import Interfaces.Command;

public class Saving implements Command
{

    public Saving() {}

    @Override
    public String execute(String[] args, Context context) throws WrongCommand {
        String filename = context.getFilename();
        if (filename == null || filename.isEmpty()) {
            throw new WrongCommand("No file is currently open. Use 'SavingAs <file>' to save to a specific file.");
        }

        try {
            context.getSaveStorage().saving(context.getElement(), filename);
        } catch (Exception e) {
            throw new WrongCommand("Error saving file: " + e.getMessage());
        }

        return "Saved " + filename;
    }

    @Override
    public String helpMsg() {
        return  "Saves file";
    }
}
