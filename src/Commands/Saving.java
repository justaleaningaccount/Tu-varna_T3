package Commands;
import Exceptions.WrongCommand;
import org.w3c.dom.*;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class Saving implements Command
{
    public void save(Element element, Writer writer) throws IOException {
        writer.write("<" + element.getTagName());

        NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Attr attr = (Attr) attrs.item(i);
            writer.write(" " + attr.getName() + "=\"" + attr.getValue() + "\"");
        }

        writer.write(">");

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                save((Element) child, writer);
            } else if (child.getNodeType() == Node.TEXT_NODE) {
                writer.write(child.getTextContent().trim());
            }
        }

        writer.write("</" + element.getTagName() + ">");
    }


    @Override
    public String execute(List<String> args) throws WrongCommand {
        return "";
    }

    @Override
    public String helpMsg() {
        return "Saves file";
    }
}
