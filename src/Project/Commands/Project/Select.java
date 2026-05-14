package Project.Commands.Project;
import Project.Commands.Context;
import Project.Exceptions.BadIndex;
import Project.Exceptions.NoElement;
import Project.Interfaces.Command;
import Project.Parts.Element;
import Project.Parts.FinderOfElem;
import java.util.Map;
/**
 * Selects and returns the value of a named attribute from an element.
 * Validates element id and attribute key, throws descriptive exceptions.
 * Useful for querying single attribute values from the document tree.
 */

public class Select implements Command
{
    public Select() {}

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
            throw new BadIndex("Id of an element must a number.");
        }

        Element found = FinderOfElem.findByResolvedId(element, targetId);
        if (found == null) {
            throw new NoElement("No element with" + targetId + " found.");
        }

        Map<String, String> attribute = found.getAttributes();
        if (!attribute.containsKey(att)) {
            throw new NoElement("Attribute '" + att + "' was not found on element with id " + targetId);
        }
        return attribute.get(att);
    }


    @Override
    public String helpMsg() {
        return "Return a value based on the attribute id and key";
    }
}
