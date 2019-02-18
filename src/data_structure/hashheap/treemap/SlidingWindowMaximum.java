package data_structure.hashheap.treemap;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SlidingWindowMaximum {
    /**
     * 2/17/2019
     * TreeSet
     *
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     */
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return new ArrayList<>();
        }

        // IntegerHashMaxHeap heap = new IntegerHashMaxHeap();
        TreeMap<Integer, Integer> map = new TreeMap<>((a, b) -> b - a);

        for (int i = 0; i <= k - 1; i++) {
            // heap.add(nums[i]);
            add(map, nums[i]);
        }

        int left = 0, right = k;
        ArrayList<Integer> list = new ArrayList<>();


        while (true) {
            // list.add(heap.peek());
            list.add(map.firstKey());


            // heap.remove(nums[left++]);
            remove(map, nums[left++]);

            if (right == nums.length) {
                break;
            } else {
                // heap.add(nums[right++]);
                add(map, nums[right++]);
            }

        }

        return list;
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
        remove(map, next);

        return next;
    }

    private void add(TreeMap<Integer, Integer> map, int next) {
        map.put(next, map.getOrDefault(next, 0) + 1);
    }
}
