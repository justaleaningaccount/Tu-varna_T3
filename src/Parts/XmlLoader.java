package Parts;

import java.io.*;
import java.util.Map;

public class XmlLoader
{
    private XmlLoader(){}

    public static void save(Element root, String filename) throws IOException
    {
        try (FileWriter fw = new FileWriter(filename, false))
        {
            StringBuilder sb = new StringBuilder();
            writeElement(root, sb, 0);
            fw.write(sb.toString());
        }
    }
    public static Element load(String filename) throws Exception
    {
        File f = new File(filename);
        if (!f.exists()) return null;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(f)))
        {
            String line;
            while ((line = br.readLine()) != null) sb.append(line).append("\n");
        }
        XmlParser parser = new XmlParser(sb.toString());
        return parser.parseElement();
    }

    public static void writeElement(Element elem, StringBuilder sb, int i)
    {
        String ind = "  ".repeat(Math.max(0, i));
        sb.append(ind).append("<").append(xmlName(elem.getName()));
        Integer rid = elem.getResolvedId();
        if (rid != null) sb.append(" id=\"").append(rid).append("\"");
        for (Map.Entry<String, String> a : elem.getAttributes().entrySet())
        {
            sb.append(" ").append(xmlName(a.getKey())).append("=\"").append(xmlAttr(a.getValue())).append("\"");
        }

        boolean hasChildren = elem.getChildren() != null && !elem.getChildren().isEmpty();
        boolean hasText = elem.getTextContent() != null && !elem.getTextContent().isEmpty();
        if (!hasChildren && !hasText)
        {
            sb.append("/>").append("\n"); return;
        }
        sb.append(">").append("\n");
        if (hasText)
        {
            sb.append("  ".repeat(i + 1)).append(xmlText(elem.getTextContent())).append("\n");
        }
        if (hasChildren)
        {
            for (Element c : elem.getChildren()) writeElement(c, sb, i + 1);
        }
        sb.append(ind).append("</").append(xmlName(elem.getName())).append(">").append("\n");
    }

    private static String xmlText(String text)
    {
        if (text == null) return "";
        else return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private static String xmlAttr(String value) {
        if (value==null) return "";
        else return value.replace("&", "&amp;").replace("\"", "&quot;")
                .replace("<", "&lt;").replace(">", "&gt;").replace("'", "&apos;");
    }

    private static String xmlName(String name) {
        if (name == null) return "element";
        else return name.replaceAll(" ", "_");
    }

    public static String xmlSymbols(String substring)
    {
        if (substring == null) return null;
        return substring.replace("&amp;", "&")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&quot;", "\"")
                .replace("&apos;", "'");
    }
}
