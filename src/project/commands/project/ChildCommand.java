package project.commands.project;

import project.commands.Context;
import project.exceptions.BadIndex;
import project.exceptions.NoElement;
import project.exceptions.NoFile;
import project.interfaces.Command;
import project.parts.Element;
import project.parts.FinderOfElem;
import project.parts.XmlLoader;
/**
 * Retrieves the nth child element of a specified parent element.
 * Validates numeric ids, checks for document presence and child bounds.
 * Serializes the selected child element to XML for display.
 */

public class ChildCommand implements Command
{
    private FinderOfElem finder;
    private XmlLoader loader;

    public ChildCommand(XmlLoader loader, FinderOfElem finder) {
        this.loader = loader;
        this.finder = finder;
    }

    public ChildCommand() {}
    @Override
    public String execute(String[] args, Context context) throws NoElement
    {
        if (args == null || args.length < 2)
        {
            throw new NoElement("Too little arguments.");
        }

        String elem = args[0];
        String child = args[1];

        if (elem == null || elem.isEmpty())
        {
            throw new NoElement("No element id.");
        }
        if (child == null || child.isEmpty())
        {
            throw new NoElement("No child id.");
        }

        Element root = context.getElement();
        if (root == null)
        {
            throw new NoFile("No document.");
        }

        int parentId; int index;
        try
        {
            parentId = Integer.parseInt(elem);
        }
        catch (Exception e)
        {
            throw new BadIndex("Wrong element id. Must be a number");
        }
        index = Integer.parseInt(child);
        if (index < 1) throw new BadIndex("Id must be < 1");

        Element e = finder.findByResolvedId(root, parentId);
        if (e == null)
        {
            throw new NoElement("No element with " + parentId + " found.");
        }

        if (e.getChildren() == null || e.getChildren().isEmpty())
        {
            throw new NoElement("Element " + parentId + " has no children.");
        }

        if (index > e.getChildren().size())
        {
            throw new BadIndex("Too big id. Element has " + e.getChildren().size() + " children.");
        }

        Element element = e.getChildren().get(index - 1);

        StringBuilder sb = new StringBuilder();
        XmlLoader loader = new XmlLoader();
        loader.writeElement(element, sb, 0);
        return sb.toString();
    }

    @Override
    public String helpMsg()
    {
        return "Make an entry to the nth inheritor of the element ";
    }
}
