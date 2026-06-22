package Project.Parts;

import Project.Exceptions.BadIndex;
import Project.Exceptions.WrongPlace;
/**
 * Lightweight XML parser that converts textual XML into Element objects.
 * Implements a simple recursive-descent parser with basic error reporting.
 * Handles attributes, nested elements and text nodes; throws WrongPlace on errors.
 */
public class XmlParser
{
    private String text;
    private int pos = 0;
    private int length;
    private XmlLoader loader;

    public XmlParser(String text, XmlLoader loader) {
        this.text = text == null ? "" : text;
        this.length = this.text.length();
        this.loader = loader;
    }

    public Element parseElement() throws WrongPlace {
        skipWhitespace();
        if (!peekStarting("<")) {
            throw new WrongPlace("Expected '<' at position " + pos);
        }
        expect('<');
        String tagName = parseName();
        Element e = new Element(tagName);

        while (true) {
            skipWhitespace();
            char c = peek();
            if (c == '/' || c == '>') {
                break;
            }
            String attrName = parseName();
            skipWhitespace();
            expect('=');
            skipWhitespace();
            String attrValue = quotedValue();
            if ("id".equals(attrName)) {
                try {
                    e.id = Integer.parseInt(attrValue);
                } catch (NumberFormatException ignored) { }
            } else {
                e.setAttribute(attrName, attrValue);
            }
        }

        skipWhitespace();
        if (peek() == '/') {
            expect('/');
            expect('>');
            return e;
        }
        expect('>');

        StringBuilder content = new StringBuilder();
        while (true) {
            skipWhitespaceButBetter();
            if (peekStarting("</")) {
                expect('<');
                expect('/');
                parseName();
                skipWhitespace();
                expect('>');
                break;
            } else if (peekStarting("<")) {
                Element child = parseElement();
                e.addChild(child);
            } else {
                String textNode = parseTextNode();
                if (textNode != null && !textNode.isBlank()) {
                    if (!content.isEmpty()) content.append(" ");
                    content.append(textNode.trim());
                }
            }
        }

        if (!content.isEmpty()) e.setTextContent(content.toString());
        return e;
    }

    private String parseTextNode() {
        int start = pos;
        while (pos < length && text.charAt(pos) != '<') pos++;
        if (pos > start) {
            return loader.xmlSymbols(text.substring(start, pos));
        }
        return null;
    }

    private String parseName() throws WrongPlace {
        skipWhitespace();
        int start = pos;
        while (pos < length) {
            char c = text.charAt(pos);
            if (Character.isWhitespace(c) || c == '=' || c == '>' || c == '/' || c == '<') break;
            pos++;
        }
        if (pos == start) {
            throw new WrongPlace("Expected name at " + pos);
        }
        return text.substring(start, pos);
    }

    private String quotedValue() throws WrongPlace {
        skipWhitespace();
        char ch = peek();
        if (ch != '"' && ch != '\'') {
            throw new WrongPlace("Expected attribute value at " + pos);
        }
        pos++;
        int start = pos;
        while (pos < length && text.charAt(pos) != ch) pos++;
        if (pos >= length) {
            throw new BadIndex("Bad attribute value");
        }
        String val = text.substring(start, pos);
        pos++;
        return loader.xmlSymbols(val);
    }

    private void skipWhitespace() {
        while (pos < length && Character.isWhitespace(text.charAt(pos))) pos++;
    }

    private void skipWhitespaceButBetter() {
        while (pos < length) {
            char c = text.charAt(pos);
            if (c == ' ' || c == '\t' || c == '\r' || c == '\n') pos++;
            else break;
        }
    }

    private char peek() {
        if (pos >= length) return (char) -1;
        return text.charAt(pos);
    }

    private boolean peekStarting(String s) {
        return text.regionMatches(pos, s, 0, s.length());
    }

    private void expect(char c) throws WrongPlace {
        if (pos >= length || text.charAt(pos) != c) {
            throw new WrongPlace("Expected '" + c + "' at position " + pos);
        }
        pos++;
    }
}
