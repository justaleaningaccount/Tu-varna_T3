package Project.Commands.Project;
import Project.Commands.Context;
import Project.Exceptions.BadIndex;
import Project.Exceptions.NoElement;
import Project.Exceptions.NoFile;
import Project.Interfaces.Command;
import Project.Parts.Element;
import Project.Parts.FinderOfElem;
import java.util.Map;
/**
 * Sets or replaces an attribute value on a specified element.
 * Validates arguments, finds the target element and updates its attributes.
 * Returns a message indicating whether the attribute was set or replaced.
 */

public class Setting implements Command
{
    @Override
    public String execute(String[] args, Context context) throws NoElement
    {
        if (args == null || args.length < 3)
        {
            throw new NoElement("Not enough attributes");
        }
        String id = args[0];
        String att = args[1];

        String value = getString(args, id, att);

        Element root = context.getElement();
        if (root == null)
        {
            throw new NoFile("No document.");
        }

        int idNeeded;
        try
        {
            idNeeded = Integer.parseInt(id);
        }
        catch (Exception e)
        {
            throw new BadIndex("Id must be a number");
        }

        Element target = FinderOfElem.findByResolvedId(root, idNeeded);
        if (target == null)
        {
            throw new NoElement("No element with " + idNeeded + " found.");
        }

        Map<String, String> attribute = target.getAttributes();
        String prev = attribute.put(att, value);
        if (prev == null)
        {
            return "Set attribute to'" + att + "' = \"" + value + "\"" + idNeeded;
        }
        else
        {
            return "Replaced attribute '" + att + "': previous=\"" + prev + "\" new=\"" + value + "\"";
        }
    }

    private static String getString(String[] args, String id, String key) {
        if (id == null || id.isEmpty()) {
            throw new NoElement("No element id.");
        }
        if (key == null || key.isEmpty()) {
            throw new NoElement("No attribute key.");
        }

        StringBuilder valueBuilder = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            if (i > 2) valueBuilder.append(" ");
            valueBuilder.append(args[i]);
        }

        return valueBuilder.toString().trim();
    }


    @Override
    public String helpMsg()
    {
        return "Set a value to an attribute";
    }
}
