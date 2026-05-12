package Project.Commands.Project;

import Project.Commands.Context;
import Project.Exceptions.BadIndex;
import Project.Exceptions.NoElement;
import Project.Exceptions.NoFile;
import Project.Interfaces.Command;
import Project.Parts.Element;
import Project.Parts.FinderOfElem;
import Project.Parts.IdMaker;

public class NewChild implements Command {

    public NewChild() {}

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

        Element parent = FinderOfElem.findByResolvedId(root, parentIds);
        Element child = getElement(args, parent, parentIds);
        int newId = IdMaker.nextResolvedId(root);
        child.id.put(0, newId);
        parent.addChild(child);

        return String.valueOf(newId);
    }

    private static Element getElement(String[] args, Element parent, int parentIds)
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
