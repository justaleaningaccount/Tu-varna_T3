package Parts;

import java.util.List;

public class IdMaker
{
    private IdMaker(){}
    public static int nextResolvedId(Element root) {
        if (root == null) return 1;
        int max = scanMaxId(root);
        return max + 1;
    }

    private static int scanMaxId(Element current) {
        int max = Integer.MIN_VALUE;
        Integer resolved = current.getResolvedId();
        if (resolved != null) max = Math.max(max, resolved);

        List<Element> children = current.getChildren();
        if (children != null) {
            for (Element child : children) {
                int childMax = scanMaxId(child);
                if (childMax > max) max = childMax;
            }
        }

        return (max == Integer.MIN_VALUE) ? 0 : max;
    }
}
