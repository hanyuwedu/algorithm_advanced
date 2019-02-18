package data_structure.DirectedGraph;

import java.util.HashMap;
import java.util.Map;

public class DirectedGraphNode {
    public int label;
    public Map<DirectedGraphNode, Integer> neighbors;

    public DirectedGraphNode(int x) {
        label = x;
        neighbors = new HashMap<>();
    }
}
