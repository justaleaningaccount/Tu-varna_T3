package Commands.Project;
import Commands.Context;
import Exceptions.WrongCommand;
import Interfaces.Command;
import Parts.Element;

import java.util.List;
import java.util.Map;

public class Select implements Command
{
    public Select() {}

    @Override
    public String execute(String[] args, Context context) throws WrongCommand {
        if (args == null || args.length < 2) {
            throw new WrongCommand("Error. Not enough arguments. Usage: Select <elementId> <attributeKey>");
        }

        String idStr = args[0];
        String attrKey = args[1];

        if (idStr == null || idStr.isEmpty()) {
            throw new WrongCommand("Error. Element identifier is empty.");
        }
        if (attrKey == null || attrKey.isEmpty()) {
            throw new WrongCommand("Error. Attribute key is empty.");
        }

        Element root = context.getElement();
        if (root == null) {
            throw new WrongCommand("No document loaded.");
        }

        int targetId;
        try {
            targetId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            throw new WrongCommand("Invalid element identifier. It must be an integer.");
        }

        Element found = findByResolvedId(root, targetId);
        if (found == null) {
            throw new WrongCommand("Element with id " + targetId + " not found.");
        }

        Map<String, String> attrs = found.getAttributes();
        if (!attrs.containsKey(attrKey)) {
            throw new WrongCommand("Attribute '" + attrKey + "' not found on element with id " + targetId + ".");
        }
        return attrs.get(attrKey);
    }

    private Element findByResolvedId(Element current, int targetId) {
        Integer resolved = current.getResolvedId();
        if (resolved != null && resolved == targetId) {
            return current;
        }
        List<Element> children = current.getChildren();
        if (children == null || children.isEmpty()) return null;

        for (Element child : children) {
            Element result = findByResolvedId(child, targetId);
            if (result != null) return result;
        }
        return null;
    }

    @Override
    public String helpMsg() {
        return "Извежда стойност на атрибут по даден идентификатор на елемента и ключ на атрибута";
    }
}
