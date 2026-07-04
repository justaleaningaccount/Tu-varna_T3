package project.parts;

import java.util.List;
/**
 * Utility for generating the next unique resolved id for an element tree.
 * Scans the tree to find the current maximum resolved id and returns max+1.
 * Handles empty trees by returning 1 as the first id.
 */
public class IdMaker
{
    private IdMaker(){}
    public int nextResolvedId(Element root) {
        if (root == null) return 1;
        int max = scanMaxId(root);
        return max + 1;
    }

    private int scanMaxId(Element current) {
        int max = Integer.MIN_VALUE;
        Integer resolved = current.getResolvedId();
        if (resolved != null) max = resolved;

        List<Element> children = current.getChildren();
        if (children != null)
        {
            for (Element child : children)
            {
                int childMax = scanMaxId(child);
                if (childMax > max)
                {
                    max = childMax;
                }
            }
        }

        return (max == Integer.MIN_VALUE) ? 0 : max;
    }
}
