package Commands;

import org.w3c.dom.*;

public class Select
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

}
