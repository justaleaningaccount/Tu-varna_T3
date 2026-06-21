package Project.Parts;

import java.util.*;

/**
 * Represents an XML-like element node with attributes, children and text.
 * Stores a map of id entries, a name, attributes map, children list and text.
 * Provides helpers to add children, set attributes and obtain resolved id.
 */
public class Element {
    public int id;
    private String name;
    private Set<Attribute> attributes = new LinkedHashSet<>();
    private List<Element> children = new ArrayList<>();
    private String textContent;

    public Element(String name) { this.name = name; }

    public String getName() { return name; }

    public Set<Attribute> getAttributesSet()
    {
        return new LinkedHashSet<>(attributes);
    }
    public String getAttribute(String key) {
        for (Attribute a : attributes) {
            if (a.getKey().equals(key)) return a.getValue();
        }
        return null;
    }

    public String setAttribute(String key, String value)
    {
        for (Attribute a : attributes)
        {
            if (a.getKey().equals(key))
            {
                String old = a.getValue();
                a.setValue(value);
                return old;
            }
        }
        attributes.add(new Attribute(key, value));
        return null;
    }

    public String removeAttribute(String key)
    {
        for (Attribute a : attributes)
        {
            if (a.getKey().equals(key))
            {
                String old = a.getValue();
                attributes.remove(a);
                return old;
            }
        }
        return null;
    }

    public List<Element> getChildren() { return children; }
    public String getTextContent() { return textContent; }
    public void setTextContent(String textContent) { this.textContent = textContent; }
    public void addChild(Element child) { children.add(child); }

    public Integer getResolvedId() { return id == 0 ? null : id; }
}