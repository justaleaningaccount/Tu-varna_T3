package Commands.Basic;

import Commands.Context;
import Exceptions.WrongCommand;
import Interfaces.Command;

public class SavingAs implements Command {

    public SavingAs() {}

    @Override
    public String execute(String[] args, Context context) throws WrongCommand {
        if (args == null || args.length < 1 || args[0] == null || args[0].isEmpty()) {
            throw new WrongCommand("Error. Not enough arguments. Usage: SavingAs <file>");
        }

        String filename = args[0];
        context.setFilename(filename);

        try {
            context.getSaveStorage().saving(context.getElement(), filename);
        } catch (Exception e) {
            throw new WrongCommand("Error saving file: " + e.getMessage());
        }

        return "Saved as " + filename;
    }

    @Override
    public String helpMsg() {
        return "Save in specific file";
    }
}
