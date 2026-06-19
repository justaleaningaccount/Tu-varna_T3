package Project.Commands.Project;

import Project.Commands.Context;
import Project.Exceptions.NoElement;
import Project.Interfaces.Command;
import Project.Parts.Element;
import Project.Parts.XmlLoader;
/**
 * Prints the entire loaded XML document (element tree) as formatted XML.
 * Throws when no element/document is loaded in the context.
 * Uses XmlLoader.writeElement to produce the textual representation.
 */

public class PrintingCommand implements Command {

    public PrintingCommand() {}

    @Override
    public String execute(String[] args, Context context) throws NoElement
    {
        Element element = context.getElement();
        if (element == null)
        {
            throw new NoElement("No element loaded to print.");
        }

        StringBuilder sb = new StringBuilder();
        XmlLoader.writeElement(element, sb, 0);
        return sb.toString();
    }
    @Override
    public String helpMsg() {
        return "Prints text as Xml";
    }
}
