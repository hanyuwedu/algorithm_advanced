package data_structure.sweepline;

import util.Interval;

import java.util.*;

public class MergeIntervals {
    /**
     * 1/4/2019
     * Sweep Line
     *
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return new ArrayList<>();
        }

        Map<Integer, Integer> start = new HashMap<>(), end = new HashMap<>();
        Set<Integer> time = new HashSet<>();

        for (Interval interval : intervals) {
            time.add(interval.start);
            time.add(interval.end);
            start.put(interval.start, start.getOrDefault(interval.start, 0) + 1);
            end.put(interval.end, end.getOrDefault(interval.end, 0) + 1);
        }

        Queue<Integer> heap = new PriorityQueue<>(time);
        int left = -1, vol = 0;
        List<Interval> list = new ArrayList<>();

        while (!heap.isEmpty()) {
            int current = heap.remove();
            if (vol == 0) {
                left = current;
            }

            if (start.containsKey(current)) {
                vol += start.get(current);
            }

            if (end.containsKey(current)) {
                vol -= end.get(current);
            }

            if (vol == 0) {
                Interval interval = new Interval(left, current);
                list.add(interval);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        Interval interval = new Interval(1, 3);

        List<Interval> input = new ArrayList<>();
        input.add(interval);

        System.out.println(new MergeIntervals().merge(input));
    }
}
