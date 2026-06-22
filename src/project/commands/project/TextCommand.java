package project.commands.project;

import project.commands.Context;
import project.exceptions.BadIndex;
import project.exceptions.NoElement;
import project.parts.Element;
import project.parts.FinderOfElem;
import project.interfaces.Command;

/**
 * Gets or sets the textContent of an Element identified by its resolved id.
 * Usage: `text id` returns the element's text; `text id text...` sets the text and confirms.
 */

public class TextCommand implements Command {
    @Override
    public String execute(String[] args, Context context) throws NoElement {
        if (args == null || args.length < 1) {
            throw new NoElement("Too little arguments.");
        }

        String idStr = args[0];
        if (idStr == null || idStr.isEmpty()) {
            throw new NoElement("No element id.");
        }

        Element root = context.getElement();
        if (root == null) {
            throw new NoElement("No document.");
        }

        int targetId;
        try {
            targetId = Integer.parseInt(idStr);
        } catch (Exception ex) {
            throw new BadIndex("Id of an element must be a number.");
        }

        Element found = FinderOfElem.findByResolvedId(root, targetId);
        if (found == null) {
            throw new NoElement("No element with " + targetId + " found.");
        }

        if (args.length == 1) {
            String text = found.getTextContent();
            return text == null ? "No text" : text;
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            if (i > 1) sb.append(" ");
            sb.append(args[i]);
        }
        String newText = sb.toString();
        found.setTextContent(newText);
        return "Set text of element " + targetId;
    }

    @Override
    public String helpMsg() {
        return "Get or set the text content of an element by id";
    }
}
