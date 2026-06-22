package project.parts;

import java.io.*;
import java.util.Set;

/**
 * Responsible for serializing and deserializing Element trees to/from XML text.
 * Provides save, load, writeElement and helper methods for XML escaping.
 * Used by SaveStorage and the interactive engine to persist documents.
 */
public class XmlLoader
{
        public void save(Element root, String filename) throws IOException
        {
            try (FileWriter fw = new FileWriter(filename, false))
            {
                StringBuilder sb = new StringBuilder();
                writeElement(root, sb, 0);
                fw.write(sb.toString());
            }
        }

        public Element load(String filename) throws Exception
        {
            File file = new File(filename);
            if (!file.exists()) return null;
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                String str;
                while ((str = br.readLine()) != null) sb.append(str).append("\n");
            }
            XmlParser parser = new XmlParser(sb.toString(),this);
            return parser.parseElement();
        }

        public void writeElement(Element elem, StringBuilder sb, int indent)
        {
            String s = "  ".repeat(Math.max(0, indent));
            sb.append(s).append("<").append(xmlName(elem.getName()));
            Integer id = elem.getResolvedId();
            if (id != null) sb.append(" id=\"").append(id).append("\"");

            Set<Attribute> attrs = elem.getAttributesSet();
            if (attrs != null && !attrs.isEmpty())
            {
                for (Attribute a : attrs)
                {
                    sb.append(" ").append(xmlName(a.getKey())).append("=\"").append(xmlAttr(a.getValue())).append("\"");
                }
            }

            boolean hasChildren = elem.getChildren() != null && !elem.getChildren().isEmpty();
            boolean hasText = elem.getTextContent() != null && !elem.getTextContent().isEmpty();
            if (!hasChildren && !hasText)
            {
                sb.append("/>").append("\n");
                return;
            }
            sb.append(">").append("\n");
            if (hasText) {
                sb.append("  ".repeat(indent + 1)).append(xmlText(elem.getTextContent())).append("\n");
            }
            if (hasChildren)
            {
                for (Element c : elem.getChildren()) writeElement(c, sb, indent + 1);
            }
            sb.append(s).append("</").append(xmlName(elem.getName())).append(">").append("\n");
        }

        private String xmlText(String text)
        {
            if (text == null) return "";
            return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
        }

        private String xmlAttr(String value)
        {
            if (value == null) return "";
            return value.replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;").replace("'", "&apos;");
        }

        private String xmlName(String name)
        {
            if (name == null) return "element";
            return name.replaceAll(" ", "_");
        }

        public String xmlSymbols(String substring)
        {
            if (substring == null) return null;
            return substring.replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">").replace("&quot;", "\"").replace("&apos;", "'");
        }
    }
