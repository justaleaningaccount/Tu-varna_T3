package Commands.Project;
import Commands.Context;
import Exceptions.WrongCommand;
import Interfaces.Command;
import Parts.Element;

import java.util.List;
import java.util.Map;

public class Printing implements Command {

    public Printing() {}

    @Override
    public String execute(String[] args, Context context) throws WrongCommand {
        Element root = context.getElement();
        if (root == null) {
            throw new WrongCommand("No element loaded to print.");
        }

        StringBuilder sb = new StringBuilder();
        buildXml(root, sb, 0);
        return sb.toString();
    }

    @Override
    public String helpMsg() {
        return "Prints current document as formatted XML";
    }

    private void buildXml(Element element, StringBuilder sb, int indentLevel) {
        String indent = "  ".repeat(Math.max(0, indentLevel));
        sb.append(indent).append("<").append(escapeXmlName(element.getName()));

        Integer resolvedId = element.getResolvedId();
        if (resolvedId != null) {
            sb.append(" id=\"").append(escapeXml(String.valueOf(resolvedId))).append("\"");
        }

        for (Map.Entry<String, String> attr : element.getAttributes().entrySet()) {
            sb.append(" ").append(escapeXmlName(attr.getKey()))
                    .append("=\"").append(escapeXml(attr.getValue())).append("\"");
        }

        List<Element> children = element.getChildren();
        String text = element.getTextContent();

        boolean hasChildren = children != null && !children.isEmpty();
        boolean hasText = text != null && !text.isEmpty();

        if (!hasChildren && !hasText) {
            sb.append("/>").append("\n");
            return;
        }

        sb.append(">").append("\n");

        if (hasText) {
            String textIndent = "  ".repeat(indentLevel + 1);
            sb.append(textIndent).append(escapeXml(text)).append("\n");
        }

        if (hasChildren) {
            for (Element child : children) {
                buildXml(child, sb, indentLevel + 1);
            }
        }

        sb.append(indent).append("</").append(escapeXmlName(element.getName())).append(">").append("\n");
    }

    private String escapeXml(String s) {
        if (s == null) return "";
        StringBuilder out = new StringBuilder();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '&': out.append("&amp;"); break;
                case '<': out.append("&lt;"); break;
                case '>': out.append("&gt;"); break;
                case '\"': out.append("&quot;"); break;
                case '\'': out.append("&apos;"); break;
                default: out.append(c);
            }
        }
        return out.toString();
    }

    private String escapeXmlName(String name) {
        if (name == null) return "element";
        return name.replaceAll("\\s+", "_");
    }
}
