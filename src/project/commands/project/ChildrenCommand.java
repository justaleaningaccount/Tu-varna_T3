package project.commands.project;

import project.commands.Context;
import project.exceptions.BadIndex;
import project.exceptions.NoElement;
import project.exceptions.NoFile;
import project.interfaces.Command;
import project.parts.Attribute;
import project.parts.Element;
import project.parts.FinderOfElem;
import java.util.List;
import java.util.Set;

/**
 * Lists all children (including nested descendants) of a specified element.
 * Validates input, finds the parent element and formats a readable hierarchical listing.
 * Returns a multi-line string describing each child and its attributes.
 */
public class ChildrenCommand implements Command {

    public ChildrenCommand() {}

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

    private void listChildren(Element parent, StringBuilder sb, int depth) {
        List<Element> children = parent.getChildren();
        if (children == null || children.isEmpty()) return;

        for (Element child : children) {
            sb.append("  ".repeat(depth));
            sb.append("- ").append(child.getName());
            Integer id = child.getResolvedId();
            if (id != null) sb.append(" (id=").append(id).append(")");
            sb.append(" - attributes: ");
            Set<Attribute> att = child.getAttributesSet();
            if (att == null || att.isEmpty()) {
                sb.append("none");
            } else {
                boolean first = true;
                for (Attribute a : att) {
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