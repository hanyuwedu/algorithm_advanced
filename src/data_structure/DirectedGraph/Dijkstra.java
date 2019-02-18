package data_structure.DirectedGraph;

import data_structure.hashheap.HashHeap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra {
    public int dijkstra(DirectedGraphNode src, DirectedGraphNode dst) {
        Map<DirectedGraphNode, Integer> distanceToSrc = new HashMap<>();
        HashHeap<DirectedGraphNode> heap = new HashHeap<>(Comparator.comparingInt(distanceToSrc::get));

        distanceToSrc.put(src, 0);
        heap.add(src);

        while (!heap.isEmpty()) {
            DirectedGraphNode current = heap.remove();
            for (DirectedGraphNode next : current.neighbors.keySet()) {
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
        DirectedGraphNode a = new DirectedGraphNode(0);
        DirectedGraphNode b = new DirectedGraphNode(1);
        DirectedGraphNode c = new DirectedGraphNode(2);
        DirectedGraphNode d = new DirectedGraphNode(3);
        DirectedGraphNode e = new DirectedGraphNode(4);
        DirectedGraphNode f = new DirectedGraphNode(5);
        DirectedGraphNode g = new DirectedGraphNode(6);
        DirectedGraphNode h = new DirectedGraphNode(7);


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

        DirectedGraphNode src = a, dst = h;
        System.out.println(new Dijkstra().dijkstra(src, dst));
    }
}
