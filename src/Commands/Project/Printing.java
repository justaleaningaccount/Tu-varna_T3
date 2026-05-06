package Commands.Project;

import Commands.Context;
import Exceptions.NoElement;
import Interfaces.Command;
import Parts.Element;
import Parts.XmlLoader;

public class Printing implements Command {

    public Printing() {}

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
