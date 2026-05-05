package Commands.Project;
import Commands.Context;
import Exceptions.WrongCommand;
import Interfaces.Command;
import Parts.Element;
import Parts.XmlLoader;

import java.util.List;
import java.util.Map;

public class Printing implements Command {

    public Printing() {}
    @Override
    public String execute(String[] args, Context context) throws WrongCommand {
        Element root = context.getElement();
        if (root == null)
        {
            throw new WrongCommand("No element loaded to print.");
        }

        StringBuilder sb = new StringBuilder();
        XmlLoader.writeElement(root, sb, 0);
        return sb.toString();
    }

    @Override
    public String helpMsg() {
        return "Prints text as Xml";
    }
}
