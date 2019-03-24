package graph.directedgraph;

import java.util.HashMap;
import java.util.Map;

public class WeightedDirectedGraphNode {
    public int label;
    public Map<WeightedDirectedGraphNode, Integer> neighbors;

    public WeightedDirectedGraphNode(int x) {
        label = x;
        neighbors = new HashMap<>();
    }
}
