package project.commands.project;

import project.commands.Context;
import project.exceptions.BadIndex;
import project.exceptions.NoElement;
import project.exceptions.NoFile;
import project.interfaces.Command;
import project.parts.Element;
import project.parts.FinderOfElem;
import project.parts.IdMaker;
/**
 * Creates a new child element under a specified parent element.
 * Generates a new resolved id using IdMaker and attaches the child.
 * Returns the newly assigned resolved id as a string.
 */

public class NewChildCommand implements Command {

    private IdMaker newId;
    private FinderOfElem finder = new FinderOfElem();

    public NewChildCommand(IdMaker newId,FinderOfElem finder) {
        this.newId = newId;
        this.finder=finder;
    }

    public NewChildCommand() {}
    @Override
    public String execute(String[] args, Context context) throws NoElement {
        if (args == null || args.length < 1) {
            throw new NoElement("Too little arguments.");
        }

        String parentId = args[0];
        if (parentId == null || parentId.isEmpty()) {
            throw new NoElement("Parent id is empty.");
        }

        Element root = context.getElement();
        if (root == null)
        {
            throw new NoFile("No document.");
        }

        int parentIds;
        try
        {
            parentIds = Integer.parseInt(parentId);
        }
        catch (Exception ex)
        {
            throw new BadIndex("Parent id must be a number.");
        }

        Element parent = finder.findByResolvedId(root, parentIds);
        Element child = getElement(args, parent, parentIds);

        parent.addChild(child);
        return String.valueOf(newId);
    }

    private Element getElement(String[] args, Element parent, int parentIds)
    {
        if (parent == null)
        {
            throw new NoElement("No element with id " + parentIds + " found.");
        }

        String name = "element";
        if (args.length > 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                if (i > 1) sb.append(" ");
                sb.append(args[i]);
            }
            String candidate = sb.toString().trim();
            if (!candidate.isEmpty()) name = candidate;
        }

        return new Element(name);
    }

    @Override
    public String helpMsg() {
        return "Creates a new child that has only id.";
    }
}
