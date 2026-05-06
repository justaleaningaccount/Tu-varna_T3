package Commands.Project;

import Commands.Context;
import Exceptions.BadIndex;
import Exceptions.NoElement;
import Exceptions.NoFile;
import Interfaces.Command;
import Parts.Element;
import Parts.FinderOfElem;

import java.util.Map;

public class Deleting implements Command {
    public Deleting() {}

    @Override
    public String execute(String[] args, Context context) throws NoElement
    {
        if (args == null || args.length < 2)
        {
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
        if (ele == null)
        {
            throw new NoFile("No document.");
        }

        int target;
        try
        {
            target = Integer.parseInt(id);
        }
        catch (Exception e)
        {
            throw new BadIndex("Id must an number");
        }

        Element target1 = FinderOfElem.findByResolvedId(ele, target);
        if (target1 == null)
        {
            throw new NoElement("No elem with" + target + " found.");
        }

        Map<String, String> att = target1.getAttributes();
        if (!att.containsKey(attribute))
        {
            throw new NoElement("Attribute '" + attribute + "' cant be found om element with id " + target);
        }

        return att.remove(attribute);
    }

    @Override
    public String helpMsg()
    {
        return "Deletes an attribute of an element by key";
    }
}
