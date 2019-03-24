package pointers.window;

import data_structure.hashheap.IntegerHashMaxHeap;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SlidingWindowMaximum {
    /*
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The maximum number inside the window at each moving
     */
    public ArrayList<Integer> maxSlidingWindow_hashheap(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return new ArrayList<>();
        }

        IntegerHashMaxHeap heap = new IntegerHashMaxHeap();
        for (int i = 0; i <= k - 1; i++) {
            heap.add(nums[i]);
        }

        int left = 0, right = k;
        ArrayList<Integer> list = new ArrayList<>();


        while (true) {
            list.add(heap.peek());

            heap.remove(nums[left++]);

            if (right == nums.length) {
                break;
            } else {
                heap.add(nums[right++]);
            }

        }

        return list;
    }


    /**
     * 2/11/2019
     * Treeset
     *
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     */
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        if (k == 0 || nums.length < k) {
            return new ArrayList<>();
        }

        TreeMap<Integer, Integer> window = new TreeMap<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i <= k - 1; i++) {
            window.put(nums[i], window.getOrDefault(nums[i], 0) + 1);
        }

        int right = k, left = 0;

        while (true) {
            list.add(window.lastKey());

            if (right == nums.length) {
                break;
            }

            window.put(nums[right], window.getOrDefault(nums[right], 0) + 1);
            window.put(nums[left], window.get(nums[left]) - 1);
            if (window.get(nums[left]) == 0) {
                window.remove(nums[left]);
            }

            left++;
            right++;
        }


        // for (int right = k; right <= nums.length; right++) {
        //     list.add(window.last());

        //     window.put(nums[k], window.getOrDefault(nums[k], 0) + 1);
        //     window.put(nums[i - k], window.get(nums[i - k] - 1));
        //     if (window.get(nums[i - k]) == 0) {
        //         window.remove(nums[i - k]));
        //     }
        // }

        return list;
    }



    public static void main(String[] args) {
        int[] input = {1,2,7,7,2};
        int k = 3;

        System.out.println(new SlidingWindowMaximum().maxSlidingWindow(input, k));
    }
}
