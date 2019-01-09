package data_structure.sweepline;

import data_structure.hashheap.HashHeap;
import data_structure.hashheap.IntegerHashHeap;

import java.util.*;

public class TheSkylineProblem {
    /**
     * 1/8/2019
     * Standardized Sweep Line
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

        HashHeap<Integer> heights = new HashHeap<>((a, b) -> b - a);
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
                heights.addAll(left.get(current));
            }

            if (right.containsKey(current)) {
                heights.removeAll(right.get(current));
            }

            int next = heights.size() == 0 ? 0 : heights.peek();

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



    /**
     * 1/4/2019
     * Sweep Line
     *
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public List<List<Integer>> buildingOutline2(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return new ArrayList<>();
        }

        Set<Integer> line = new HashSet<>();
        Map<Integer, List<Integer>> left = new HashMap<>(), right = new HashMap<>();

        HashHeap<Integer> heights = new HashHeap<>((a, b) -> b - a);
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

            if (heap.isEmpty()) {
                start = current;
            }

            heights.addAll(left.get(current));
            heights.removeAll(right.get(current));

            int next = heights.size() == 0 ? 0 : heights.peek();

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




    public class IntegerHashMaxHeap {
        private List<Integer> heap;
        private Map<Integer, Integer> count;
        private Map<Integer, Integer> position;
        int size;

        public IntegerHashMaxHeap() {
            this.heap = new ArrayList<>();
            this.count = new HashMap<>();
            this.position = new HashMap<>();
            this.size = 0;
        }

        public void add(int next) {
            if (this.count.containsKey(next)) {
                this.count.put(next, this.count.get(next) + 1);
            } else {
                heap.add(next);
                this.count.put(next, 1);
                int lastIndex = heap.size() - 1;
                this.position.put(next, lastIndex);
                this.size++;

                this.siftUp(lastIndex);
            }
        }

        public Integer remove() {
            if (this.heap.isEmpty()) {
                return null;
            }

            int next = this.peek();
            this.remove();
            return next;
        }

        public boolean remove(int next) {
            if (!this.count.containsKey(next)) {
                return false;
            }

            if (this.count.get(next) > 1) {
                this.count.put(next, this.count.get(next) - 1);
            } else {
                this.count.remove(next);
                int index = this.position.get(next);
                if (index == this.heap.size() - 1) {
                    this.heap.remove(this.heap.size() - 1);
                    this.count.remove(next);
                    this.position.remove(next);
                } else {
                    this.swap(index, this.heap.size() - 1);
                    this.count.remove(next);
                    this.position.remove(next);

                    this.siftUp(index);
                    this.siftDown(index);
                }
            }

            this.size--;
            return true;
        }

        public Integer peek() {
            return heap.isEmpty() ? null : heap.get(0);
        }

        public int size() {
            return this.size;
        }

        private void siftUp(int index) {
            while (index >= 0) {
                int parent = this.getParentIndex(index);
                if (this.heap.get(parent) > this.heap.get(index)) {
                    break;
                } else {
                    this.swap(index, parent);
                    index = parent;
                }
            }
        }

        private void siftDown(int index) {
            while (true) {
                int left = getLeftChildIndex(index);
                int right = getRightChildIndex(index);

                if (left > this.heap.size() - 1) {
                    break;
                }

                int max = this.heap.get(left);

                if (right <= this.heap.size() - 1 && this.heap.get(right) > this.heap.get(left)) {
                    max = right;
                }

                if (this.heap.get(max) < this.heap.get(index)) {
                    break;
                } else {
                    this.swap(index, max);
                    index = max;
                }
            }
        }

        private void swap(int left, int right) {
            int temp = this.heap.get(left);
            this.heap.set(left, this.heap.get(right));
            this.heap.set(right, temp);

            this.position.put(this.heap.get(left), left);
            this.position.put(this.heap.get(right), right);
        }

        private int getParentIndex(int current) {
            return (current - 1) / 2;
        }

        private int getLeftChildIndex(int current) {
            return current * 2 + 1;
        }

        private int getRightChildIndex(int current) {
            return current * 2 + 2;
        }
    }
}
