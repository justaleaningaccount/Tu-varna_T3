package Project.Commands.Project;

import Project.Commands.Context;
import Project.Exceptions.BadIndex;
import Project.Exceptions.NoElement;
import Project.Exceptions.NoFile;
import Project.Interfaces.Command;
import Project.Parts.Element;
import Project.Parts.FinderOfElem;
import java.util.List;
import java.util.Map;
/**
 * Lists all children of a specified element with their attributes and ids.
 * Validates input, finds the parent element and formats a readable listing.
 * Returns a multi-line string describing each child and its attributes.
 */

public class Children implements Command
{
    public Children(){}

    @Override
    public String execute(String[] args, Context context) throws NoElement
    {
        if (args == null || args.length < 1)
        {
            throw new NoElement("Too little arguments.");
        }

        String elem = args[0];
        if (elem == null || elem.isEmpty())
        {
            throw new NoElement("No elem id");
        }

        Element element = context.getElement();
        if (element == null) {
            throw new NoFile("No document.");
        }

        int parentId;
        try
        {
            parentId = Integer.parseInt(elem);
        }
        catch (Exception e)
        {
            throw new BadIndex("Wrong element id, must be a number");
        }

        Element e = FinderOfElem.findByResolvedId(element, parentId);
        if (e == null)
        {
            throw new NoElement("No element with " + parentId + " found.");
        }
        List<Element> children = e.getChildren();
        if (children == null || children.isEmpty())
        {
            return "Element " + parentId + " has no children.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Children of element ").append(e.getName()).append(" (id=").append(e.getResolvedId()).append("):").append("\n");

        for (int i = 0; i < children.size(); i++) {

            Element child = children.get(i);
            sb.append(i + 1).append(". ");
            sb.append(child.getName());
            Integer id = child.getResolvedId();
            if (id != null) sb.append(" (id=").append(id).append(")");
            sb.append(" - attribute: ");
            Map<String, String> att = child.getAttributes();
            if (att == null || att.isEmpty())
            {
                sb.append("none");
            }
            else
            {
                boolean first = true;
                for (Map.Entry<String, String> a : att.entrySet())
                {
                    if (!first) sb.append(", ");
                    sb.append(a.getKey()).append("=\"").append(a.getValue()).append("\"");
                    first = false;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String helpMsg()
    {
        return "List all children of a element";
    }
}
