package project.commands.project;

import project.commands.Context;
import project.exceptions.NoElement;
import project.interfaces.Command;
import project.parts.Element;
import project.parts.XmlLoader;
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
        XmlLoader loader = new XmlLoader();
        loader.writeElement(element, sb, 0);
        return sb.toString();
    }
    @Override
    public String helpMsg() {
        return "Prints text as Xml";
    }
}
