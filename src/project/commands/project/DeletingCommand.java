package project.commands.project;

import project.commands.Context;
import project.exceptions.BadIndex;
import project.exceptions.NoElement;
import project.exceptions.NoFile;
import project.interfaces.Command;
import project.parts.Element;
import project.parts.FinderOfElem;

/**
 * Deletes an attribute from a specified element by key.
 * Validates element id and attribute presence, throws on errors.
 * Returns the removed attribute value or throws if not found.
 */

public class DeletingCommand implements Command {
    public DeletingCommand() {}

    @Override
    public String execute(String[] args, Context context) throws NoElement {
        if (args == null || args.length < 2) {
            throw new NoElement("Too little arguments.");
        }

        String id = args[0];
        String attribute = args[1];

        if (id == null || id.isEmpty()) {
            throw new NoElement("No element id");
        }
        if (attribute == null || attribute.isEmpty()) {
            throw new NoElement("No attribute id");
        }

        Element ele = context.getElement();
        if (ele == null) {
            throw new NoFile("No document.");
        }

        int target;
        try {
            target = Integer.parseInt(id);
        } catch (Exception e) {
            throw new BadIndex("Id must be a number");
        }

        Element target1 = FinderOfElem.findByResolvedId(ele, target);
        if (target1 == null) {
            throw new NoElement("No elem with " + target + " found.");
        }

        String existing = target1.getAttribute(attribute);
        if (existing == null) {
            throw new NoElement("Attribute '" + attribute + "' can't be found on element with id " + target);
        }

        String removed = target1.removeAttribute(attribute);
        return "Deleted attribute " + removed;
    }

    @Override
    public String helpMsg() {
        return "Deletes an attribute of an element by key";
    }
}