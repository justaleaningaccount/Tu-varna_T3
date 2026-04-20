package Parts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Element
{
    public Map<Integer,Integer> id = new LinkedHashMap<>();
    private String name;
    private Map<String,String> attributes = new LinkedHashMap<>();
    private List<Element> children = new ArrayList<>();
    private String textContent;

    public Element(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public List<Element> getChildren() {
        return children;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
    public void addChild(Element child) { children.add(child); }
    public void setAttribute(String k, String v) { attributes.put(k, v); }

    // convenience: get resolved id (first value in map)
    public Integer getResolvedId() {
        if (id.isEmpty()) return null;
        return id.values().iterator().next();
    }

    @Override
    public String toString() {
        return "XmlElement{name=" + name + ", resolvedId=" + getResolvedId() + ", attrs=" + attributes + "}";
    }
}
