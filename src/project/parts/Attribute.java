package project.parts;

public class Attribute {
    private final String key;
    private String value;

    public Attribute(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() { return key; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;
        Attribute a = (Attribute) o;
        return key.equals(a.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
}
