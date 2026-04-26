package Commands.Project;
import Commands.Context;
import Exceptions.WrongCommand;
import Interfaces.Command;
import org.w3c.dom.*;

import java.util.List;

public class Setting implements Command
{
    public boolean set(Document doc, String id, String key, String value) {
        NodeList elements = doc.getElementsByTagName("*");
        for (int i = 0; i < elements.getLength(); i++) {
            Element el = (Element) elements.item(i);
            if (id.equals(el.getAttribute("id"))) {
                el.setAttribute(key, value);
                return true;
            }
        }
        return false;
    }

    @Override
    public String execute(String[] args, Context context) throws WrongCommand {
        return "";
    }

    @Override
    public String helpMsg() {
        return "Set a value to an attribute";
    }
}
