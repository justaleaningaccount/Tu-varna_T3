package Commands.Project;

import Commands.Context;
import Exceptions.WrongCommand;
import Interfaces.Command;
import org.w3c.dom.*;

import java.util.List;

public class Select implements Command
{
    public String select(Document doc, String id, String key) {
        NodeList elements = doc.getElementsByTagName("*");
        for (int i = 0; i < elements.getLength(); i++) {
            Element el = (Element) elements.item(i);
            if (id.equals(el.getAttribute("id"))) {
                return el.getAttribute(key);
            }
        }
        return "Nqma";
    }

    @Override
    public String execute(String[] args, Context context) throws WrongCommand {
        return "";
    }

    @Override
    public String helpMsg() {
        return "Извежда стойност на атрибут по даден идентификатор на елемента и ключ на атрибута";
    }
}
