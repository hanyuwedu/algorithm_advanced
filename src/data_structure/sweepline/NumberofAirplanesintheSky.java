package data_structure.sweepline;

import util.Interval;

import java.util.*;

public class NumberofAirplanesintheSky {



    /**
     * 1/4/2019
     * Sweep line
     *
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }

        Map<Integer, Integer> takeOff = new HashMap<>(), land = new HashMap<>();
        Set<Integer> time = new HashSet<>();

        for (Interval interval : airplanes) {
            time.add(interval.start);
            time.add(interval.end);
            takeOff.put(interval.start, takeOff.getOrDefault(interval.start, 0) + 1);
            land.put(interval.end, land.getOrDefault(interval.end, 0) + 1);
        }

        Queue<Integer> heap = new PriorityQueue<>(time);
        int current = 0, max = 0;

        while (!heap.isEmpty()) {
            int next = heap.remove();
            if (land.containsKey(next)) {
                current -= land.get(next);
            }

            if (takeOff.containsKey(next)) {
                current += takeOff.get(next);
            }

            max = Math.max(max, current);
        }

        return max;
    }
}
