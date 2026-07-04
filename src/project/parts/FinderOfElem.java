package project.parts;

import java.util.List;
/**
 * Utility class to find an element by its resolved id within a tree.
 * Performs a recursive depth-first search returning the first match.
 * Returns null when the element is not present.
 */

public class FinderOfElem
{
    private FinderOfElem(){}
    public  Element findByResolvedId(Element root, int targetId) {
        if (root == null) return null;
        Integer resolved = root.getResolvedId();
        if (resolved != null && resolved == targetId) return root;

        List<Element> children = root.getChildren();
        if (children == null || children.isEmpty()) return null;

        for (Element child : children) {
            Element found = findByResolvedId(child, targetId);
            if (found != null) return found;
        }
        return null;
    }
}
