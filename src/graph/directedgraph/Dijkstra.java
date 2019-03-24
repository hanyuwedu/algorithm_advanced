package graph.directedgraph;

import data_structure.hashheap.HashHeap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Dijkstra {
    /**
     * 3/14/2019
     * TreeSet
     *
     * @param src source node in the directed graph node
     * @param dst destination node in the directed graph node
     * @return shortest distance from src to dst
     */
    public int dijkstra(WeightedDirectedGraphNode src, WeightedDirectedGraphNode dst) {
        Map<WeightedDirectedGraphNode, Integer> distToSrc = new HashMap<>();
        TreeSet<WeightedDirectedGraphNode> heap = new TreeSet<>(Comparator.comparingInt(distToSrc::get));

        distToSrc.put(src, 0);
        heap.add(src);

        while (!heap.isEmpty()) {
            WeightedDirectedGraphNode current = pop(heap);
            for (WeightedDirectedGraphNode next : current.neighbors.keySet()) {
                int nextDist = distToSrc.get(current) + current.neighbors.get(next);
                if (!distToSrc.containsKey(next)) {
                    distToSrc.put(next, nextDist);
                    heap.add(next);
                } else {
                    if (nextDist < distToSrc.get(next)) {
                        remove(heap, next);
                        distToSrc.put(next, nextDist);
                        heap.add(next);
                    }
                }
            }
        }

        return distToSrc.getOrDefault(dst, -1);
    }

    private void remove(TreeSet<WeightedDirectedGraphNode> heap, WeightedDirectedGraphNode node) {
        heap.remove(node);
    }

    private WeightedDirectedGraphNode pop(TreeSet<WeightedDirectedGraphNode> heap) {
        WeightedDirectedGraphNode first = heap.first();
        heap.remove(first);
        return first;
    }

    /**
     * 2/12/2019
     * Hashheap
     *
     * @param src source node in the directed graph node
     * @param dst destination node in the directed graph node
     * @return shortest distance from src to dst
     */
    public int dijkstra_hashheap(WeightedDirectedGraphNode src, WeightedDirectedGraphNode dst) {
        Map<WeightedDirectedGraphNode, Integer> distanceToSrc = new HashMap<>();
        HashHeap<WeightedDirectedGraphNode> heap = new HashHeap<>(Comparator.comparingInt(distanceToSrc::get));

        distanceToSrc.put(src, 0);
        heap.add(src);

        while (!heap.isEmpty()) {
            WeightedDirectedGraphNode current = heap.remove();
            for (WeightedDirectedGraphNode next : current.neighbors.keySet()) {
                int distance = current.neighbors.get(next) + distanceToSrc.get(current);
                if (!distanceToSrc.containsKey(next)) {
                    distanceToSrc.put(next, distance);
                    heap.add(next);
                } else {
                    if (distanceToSrc.get(next) > distance) {
                        heap.remove(next);
                        distanceToSrc.put(next, distance);
                        heap.add(next);
                    }
                }
            }
        }

        return distanceToSrc.containsKey(dst) ? distanceToSrc.get(dst) : -1;
    }

    public static void main(String[] args) {
        WeightedDirectedGraphNode a = new WeightedDirectedGraphNode(0);
        WeightedDirectedGraphNode b = new WeightedDirectedGraphNode(1);
        WeightedDirectedGraphNode c = new WeightedDirectedGraphNode(2);
        WeightedDirectedGraphNode d = new WeightedDirectedGraphNode(3);
        WeightedDirectedGraphNode e = new WeightedDirectedGraphNode(4);
        WeightedDirectedGraphNode f = new WeightedDirectedGraphNode(5);
        WeightedDirectedGraphNode g = new WeightedDirectedGraphNode(6);
        WeightedDirectedGraphNode h = new WeightedDirectedGraphNode(7);

        a.neighbors.put(b, 4);
        a.neighbors.put(c, 3);
        a.neighbors.put(e, 7);

        b.neighbors.put(a, 4);
        b.neighbors.put(c, 6);
        b.neighbors.put(d, 5);

        c.neighbors.put(a, 3);
        c.neighbors.put(b, 6);
        c.neighbors.put(d, 11);
        c.neighbors.put(e, 8);

        d.neighbors.put(b, 5);
        d.neighbors.put(c, 11);
        d.neighbors.put(e, 2);
        d.neighbors.put(f, 2);
        d.neighbors.put(g, 10);

        e.neighbors.put(a, 7);
        e.neighbors.put(c, 8);
        e.neighbors.put(d, 2);
        e.neighbors.put(g, 5);

        f.neighbors.put(d, 2);
        f.neighbors.put(g, 3);

        g.neighbors.put(d, 10);
        g.neighbors.put(e, 5);
        g.neighbors.put(f, 3);

        WeightedDirectedGraphNode src = a, dst = g;
        System.out.println(new Dijkstra().dijkstra(src, dst));
    }
}
