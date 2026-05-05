package Parts;

import Exceptions.WrongPlace;

public class XmlParser
{
    private final String text;
    private int pos = 0;
    private final int len;

    public XmlParser(String text)
    {
        this.text = text == null ? "" : text;
        this.len = this.text.length();
    }

    public Element parseElement() throws WrongPlace {
        skipWhitespace();
        if (!peekStartsWith("<"))
        {
            throw new WrongPlace("Expected '<' at position " + pos);
        }
        expect('<');
        String tagName = parseName();
        Element e = new Element(tagName);

        while (true)
        {
            skipWhitespace();
            char c = peek();
            if (c == '/' || c == '>') break;
            String attrName = parseName();
            skipWhitespace();
            expect('=');
            skipWhitespace();
            String attrValue = parseQuotedValue();
            if ("id".equals(attrName))
            {
                try
                {
                    int idVal = Integer.parseInt(attrValue);
                    e.id.put(0, idVal);
                }
                catch (NumberFormatException ignored) {}
            }
            else
            {
                e.setAttribute(attrName, attrValue);
            }
        }

        skipWhitespace();
        if (peek() == '/')
        {
            expect('/');
            expect('>');
            return e;
        }

        expect('>');

        StringBuilder textContent = new StringBuilder();
        while (true) {
            skipWhitespacePreserveNewline();
            if (peekStartsWith("</"))
            {
                expect('<');
                expect('/');
                parseName();
                skipWhitespace();
                expect('>');
                break;
            }
            else if (peekStartsWith("<"))
            {
                Element child = parseElement();
                e.addChild(child);
            }
            else
            {
                String t = parseTextNode();
                if (t != null && !t.isBlank())
                {
                    if (!textContent.isEmpty()) textContent.append(" ");
                    textContent.append(t.trim());
                }
            }
        }
        if (!textContent.isEmpty()) e.setTextContent(textContent.toString());
        return e;
    }

    private String parseTextNode()
    {
        int start = pos;
        while (pos < len && text.charAt(pos) != '<')
            pos++;
        if (pos > start) return XmlLoader.xmlSymbols(text.substring(start, pos));
        return null;
    }

    private String parseName() throws WrongPlace
    {
        skipWhitespace();
        int start = pos;
        while (pos < len)
        {
            char c = text.charAt(pos);
            if (Character.isWhitespace(c) || c == '=' || c == '>' || c == '/' || c == '<') break;
            pos++;
        }
        if (pos == start)
        {
            throw new WrongPlace("Expected name at position " + pos);
        }
        return text.substring(start, pos);
    }

    private String parseQuotedValue() throws WrongPlace {
        skipWhitespace();
        char q = peek();
        if (q != '"' && q != '\'')
        {
            throw new WrongPlace("Expected quoted attribute value at " + pos);
        }
        pos++;
        int start = pos;
        while (pos < len && text.charAt(pos) != q) pos++;
        if (pos >= len)
        {
            throw new WrongPlace("Unterminated attribute value");
        }
        String val = text.substring(start, pos);
        pos++;
        return XmlLoader.xmlSymbols(val);
    }

    private void skipWhitespace()
    {
        while (pos < len && Character.isWhitespace(text.charAt(pos))) pos++;
    }

    private void skipWhitespacePreserveNewline()
    {
        while (pos < len && (text.charAt(pos) == ' ' || text.charAt(pos) == '\t' || text.charAt(pos) == '\r' || text.charAt(pos) == '\n')) pos++;
    }

    private char peek()
    {
        if (pos >= len) return (char) -1;
        return text.charAt(pos);
    }

    private boolean peekStartsWith(String s)
    {
        return text.regionMatches(pos, s, 0, s.length());
    }

    private void expect(char c) throws WrongPlace
    {
        if (pos >= len || text.charAt(pos) != c)
        {
            throw new WrongPlace("Expected '" + c + "' at position " + pos);
        }
        pos++;
    }
}
