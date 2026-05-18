package Project.Commands.Basic;

import Project.Commands.Context;
import Project.Exceptions.NoElement;
import Project.Exceptions.NoFile;
import Project.Interfaces.Command;
import Project.Parts.Element;
import java.io.File;

public class Open implements Command {
    
    @Override
    public String execute(String[] args, Context context) throws NoElement {
        if (args == null || args.length < 1 || args[0] == null || args[0].isEmpty()) {
            throw new NoElement("Too little arguments. Usage: open <filename>");
        }
        
        String filename = args[0];

        try {
            File file = new File(filename);
            Element rootElement;

            if (file.exists()) {
                rootElement = context.getSaveStorage().load(filename);
                
                if (rootElement == null) {
                    rootElement = new Element("root");
                    rootElement.id.put(0, 1);
                }
            } else {
                rootElement = new Element("root");
                rootElement.id.put(0, 1);
                context.getSaveStorage().saving(rootElement, filename);
            }

            context.setFilename(filename);
            context.setElement(rootElement);

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