package project.commands.project;
import project.commands.Context;
import project.exceptions.BadIndex;
import project.exceptions.NoElement;
import project.interfaces.Command;
import project.parts.Element;
import project.parts.FinderOfElem;
/**
 * Selects and returns the value of a named attribute from an element.
 * Validates element id and attribute key, throws descriptive exceptions.
 * Useful for querying single attribute values from the document tree.
 */

public class SelectCommand implements Command
{
    private FinderOfElem finder = new FinderOfElem();

    public SelectCommand(FinderOfElem finder) {
        this.finder = finder;
    }

    public SelectCommand() {}

    @Override
    public String execute(String[] args, Context context) throws NoElement
    {
        if (args == null || args.length < 2)
        {
            throw new NoElement("Too little arguments");
        }

        String id = args[0];
        String att = args[1];

        if (id == null || id.isEmpty()) {
            throw new NoElement("No element id");
        }
        if (att == null || att.isEmpty()) {
            throw new NoElement("No attribute id");
        }

        Element element = context.getElement();
        if (element == null) {
            throw new NoElement("No document.");
        }

        int targetId;
        try
        {
            targetId = Integer.parseInt(id);
        } catch (Exception e) {
            throw new BadIndex("Id of an element must be a number.");
        }

        Element found = finder.findByResolvedId(element, targetId);
        if (found == null) {
            throw new NoElement("No element with id " + targetId + " found.");
        }

        String value = found.getAttribute(att);
        if (value == null) {
            throw new NoElement("Attribute '" + att + "' was not found on element with id " + targetId);
        }
        return value;
    }

    @Override
    public String helpMsg() {
        return "Return a value based on the attribute id and key";
    }
}