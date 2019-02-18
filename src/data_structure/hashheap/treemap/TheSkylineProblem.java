package data_structure.hashheap.treemap;

import java.util.*;

public class TheSkylineProblem {
    /**
     * 2/17/2019
     * Sweep Line with TreeMap
     *
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public List<List<Integer>> buildingOutline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return new ArrayList<>();
        }

        Set<Integer> line = new HashSet<>();
        Map<Integer, List<Integer>> left = new HashMap<>(), right = new HashMap<>();

        // HashHeap<Integer> heights = new HashHeap<>((a, b) -> b - a);
        TreeMap<Integer, Integer> heights = new TreeMap<>((a, b) -> b - a);
        List<List<Integer>> outlines = new ArrayList<>();

        for (int[] building : buildings) {
            line.add(building[0]);
            line.add(building[1]);

            if (!left.containsKey(building[0])) {
                left.put(building[0], new ArrayList<>());
            }
            left.get(building[0]).add(building[2]);

            if (!right.containsKey(building[1])) {
                right.put(building[1], new ArrayList<>());
            }
            right.get(building[1]).add(building[2]);
        }

        Queue<Integer> heap = new PriorityQueue<>(line);
        int max = 0, start = 0;

        while (!heap.isEmpty()) {
            int current = heap.remove();

            if (heights.isEmpty()) {
                start = current;
            }

            if (left.containsKey(current)) {
                // heights.addAll(left.get(current));
                for (int next : left.get(current)) {
                    add(heights, next);
                }
            }

            if (right.containsKey(current)) {
                // heights.removeAll(right.get(current));
                for (int next : right.get(current)) {
                    remove(heights, next);
                }
            }

            // int next = heights.size() == 0 ? 0 : heights.peek();
            int next = heights.size() == 0 ? 0 : heights.firstKey();

            if (next == max || max == 0) {
                max = next;
                continue;
            } else {
                List<Integer> building = new ArrayList<>();
                building.add(start);
                building.add(current);
                building.add(max);
                outlines.add(building);

                start = current;
                max = next;
            }
        }

        return outlines;
    }


    private boolean remove(TreeMap<Integer, Integer> map, int next) {
        if (!map.containsKey(next)) {
            return false;
        }

        if (map.get(next) == 1) {
            map.remove(next);
        } else {
            map.put(next, map.get(next) - 1);
        }

        return true;
    }

    private int remove(TreeMap<Integer, Integer> map) {
        int next = map.firstKey();

        if (map.get(next) == 1) {
            map.remove(next);
        } else {
            map.put(next, map.get(next) - 1);
        }

        return next;
    }

    private void add(TreeMap<Integer, Integer> map, int next) {
        map.put(next, map.getOrDefault(next, 0) + 1);
    }
}
