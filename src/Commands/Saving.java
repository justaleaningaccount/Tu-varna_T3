package Commands;
import org.w3c.dom.*;

import java.io.IOException;
import java.io.Writer;

public class Saving
{
    public void saveManually(Element element, Writer writer) throws IOException {
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
                saveManually((Element) child, writer);
            } else if (child.getNodeType() == Node.TEXT_NODE) {
                writer.write(child.getTextContent().trim());
            }
        }

        writer.write("</" + element.getTagName() + ">");
    }


}
