package Parts;

import java.util.List;

public class FinderOfElem
{
    private FinderOfElem(){}
    public static Element findByResolvedId(Element root, int targetId) {
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
