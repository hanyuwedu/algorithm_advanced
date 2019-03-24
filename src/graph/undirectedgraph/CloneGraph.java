package graph.undirectedgraph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {
    /**
     * 3/11/2019
     * BFS
     *
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        Map<UndirectedGraphNode, UndirectedGraphNode> image = new HashMap<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            UndirectedGraphNode current = queue.remove();
            image.put(current, new UndirectedGraphNode(current.label));

            for (UndirectedGraphNode neighbor : current.neighbors) {
                if (image.containsKey(neighbor)) {
                    continue;
                }
                queue.add(neighbor);
            }
        }

        for (UndirectedGraphNode current : image.keySet()) {
            for (UndirectedGraphNode neighbor : current.neighbors) {
                image.get(current).neighbors.add(image.get(neighbor));
            }
        }

        return image.get(node);
    }
}
