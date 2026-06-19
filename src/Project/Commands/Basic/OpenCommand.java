package Project.Commands.Basic;

import Project.Commands.Context;
import Project.Exceptions.NoElement;
import Project.Exceptions.NoFile;
import Project.Interfaces.Command;
import Project.Parts.Element;
import java.io.File;

public class OpenCommand implements Command {
    
    @Override
    public String execute(String[] args, Context context) throws NoElement {
        if (args == null || args.length < 1 || args[0] == null || args[0].isEmpty()) {
            throw new NoElement("Too little arguments. Usage: open <filename>");
        }
        
        String filename = args[0];
        if (!filename.toLowerCase().endsWith(".xml")) {
            filename += ".xml";
        }
        try {
            File file = new File(filename);
            Element root;

            if (file.exists()) {
                root = context.getSaveStorage().load(filename);

                if (root == null) {
                    root = new Element("root");
                    root.id = 1;
                }
            } else {
                root = new Element("root");
                root.id = 1;
                context.getSaveStorage().saving(root, filename);
            }


            context.setFilename(filename);
            context.setElement(root);

            return "Opened file " + filename;

        } catch (Exception e) {
            throw new NoFile("Error opening or creating file '" + filename + "': " + e.getMessage());
        }
    }

    @Override
    public String helpMsg() {
        return "Opens file, if file doesnt exist, create new one";
    }
}