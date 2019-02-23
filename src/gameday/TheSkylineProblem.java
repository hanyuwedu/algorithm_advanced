package gameday;

import data_structure.hashheap.HashHeap;

import java.util.*;

public class TheSkylineProblem {
    /**
     * 2/23/2019
     * GameDay
     * https://www.lintcode.com/problem/the-skyline-problem/description
     *
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public List<List<Integer>> buildingOutline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return new ArrayList<>();
        }

        Map<Integer, List<Integer>> in = new HashMap<>(), out = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int[] building : buildings) {
            set.add(building[0]);
            set.add(building[1]);

            if (!in.containsKey(building[0])) {
                in.put(building[0], new ArrayList<>());
            }
            in.get(building[0]).add(building[2]);

            if (!out.containsKey(building[1])) {
                out.put(building[1], new ArrayList<>());
            }
            out.get(building[1]).add(building[2]);
        }

        Queue<Integer> timeline = new PriorityQueue<>(set);
        List<List<Integer>> outline = new ArrayList<>();

        int start = -1, height = 0;
        TreeMap<Integer, Integer> currentHeights = new TreeMap<>((a, b) -> b - a);

        while(!timeline.isEmpty()) {
            int next = timeline.remove();

            if (in.containsKey(next)) {
                for (int h : in.get(next)) {
                    add(currentHeights, h);
                }
            }


            if (out.containsKey(next)) {
                for (int h : out.get(next)) {
                    remove(currentHeights, h);
                }
            }

            int nextHeight = currentHeights.isEmpty() ? 0 : peek(currentHeights);

            if (height == 0) {
                start = next;
                height = nextHeight;
            } else {
                if (nextHeight != height) {
                    List<Integer> currentBuilding = new ArrayList<>();
                    currentBuilding.add(start);
                    currentBuilding.add(next);
                    currentBuilding.add(height);
                    outline.add(currentBuilding);

                    start = next;
                    height = nextHeight;
                }
            }

        }

        return outline;
    }

    private void remove(TreeMap<Integer, Integer> currentHeights, int height) {
        currentHeights.put(height, currentHeights.get(height) - 1);
        if (currentHeights.get(height) == 0) {
            currentHeights.remove(height);
        }
    }

    private void add(TreeMap<Integer, Integer> currentHeights, int height) {
        currentHeights.put(height, currentHeights.getOrDefault(height, 0) + 1);
    }

    private int peek(TreeMap<Integer, Integer> currentHeights) {
        return currentHeights.firstKey();
    }
}
