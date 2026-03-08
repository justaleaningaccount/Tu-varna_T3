package Commands;
import org.w3c.dom.*;
public class Setting
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

}
