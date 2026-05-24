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
 * Lists all children (including nested descendants) of a specified element.
 * Validates input, finds the parent element and formats a readable hierarchical listing.
 * Returns a multi-line string describing each child and its attributes.
 */
public class Children implements Command {

    public Children() {}

    @Override
    public String execute(String[] args, Context context) throws NoElement {
        if (args == null || args.length < 1) {
            throw new NoElement("Too little arguments.");
        }

        String elem = args[0];
        if (elem == null || elem.isEmpty()) {
            throw new NoElement("No element id.");
        }

        Element element = context.getElement();
        if (element == null) {
            throw new NoFile("No document.");
        }

        int parentId;
        try {
            parentId = Integer.parseInt(elem);
        } catch (Exception e) {
            throw new BadIndex("Wrong element id, must be a number.");
        }

        Element parent = FinderOfElem.findByResolvedId(element, parentId);
        if (parent == null) {
            throw new NoElement("No element with id " + parentId + " found.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Children of element ").append(parent.getName())
                .append(" (id=").append(parent.getResolvedId()).append("):\n");

        listChildren(parent, sb, 1);

        return sb.toString();
    }

    private static void listChildren(Element parent, StringBuilder sb, int depth) {
        List<Element> children = parent.getChildren();
        if (children == null || children.isEmpty()) return;

        for (Element child : children) {
            sb.append("  ".repeat(depth));
            sb.append("- ").append(child.getName());
            Integer id = child.getResolvedId();
            if (id != null) sb.append(" (id=").append(id).append(")");
            sb.append(" - attributes: ");

            Map<String, String> att = child.getAttributes();
            if (att == null || att.isEmpty()) {
                sb.append("none");
            } else {
                boolean first = true;
                for (Map.Entry<String, String> a : att.entrySet()) {
                    if (!first) sb.append(", ");
                    sb.append(a.getKey()).append("=\"").append(a.getValue()).append("\"");
                    first = false;
                }
            }
            sb.append("\n");
            listChildren(child, sb, depth + 1);
        }
    }

    @Override
    public String helpMsg() {
        return "Lists all children and nested descendants of an element.";
    }
}
