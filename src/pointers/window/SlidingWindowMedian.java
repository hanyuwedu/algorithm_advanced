package pointers.window;

import data_structure.hashheap.HashHeap;

import java.util.ArrayList;
import java.util.List;

public class SlidingWindowMedian {
    /**
     * 1/22/2019
     * Chasing pointers
     *
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || nums.length == 0) {
            return new ArrayList<>();
        }

        HashHeap<Integer> left = new HashHeap<>((a, b) -> b - a),
                right = new HashHeap<>((a, b) -> a - b);
        left.add(nums[0]);

        for (int i = 1; i <= k - 1; i++) {
            if (nums[i] > left.peek()) {
                right.add(nums[i]);
            } else {
                left.add(nums[i]);
            }

            while (left.size() > right.size() + 1) {
                right.add(left.remove());
            }

            while (left.size() < right.size()) {
                left.add(right.remove());
            }
        }

        int i = 0, j = k;
        List<Integer> list = new ArrayList<>();

        while (true) {
            list.add(left.peek());

            if (j == nums.length) {
                break;
            }

            if (nums[j] <= left.peek()) {
                left.add(nums[j]);
            } else {
                right.add(nums[j]);
            }

            j++;


            if (!left.remove(nums[i])) {
                right.remove(nums[i]);
            }

            i++;

            while (left.size() > right.size() + 1) {
                right.add(left.remove());
            }

            while (left.size() < right.size()) {
                left.add(right.remove());
            }
        }

        return list;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,7,7,2};
        int k = 3;

        System.out.println(new SlidingWindowMedian().medianSlidingWindow(nums, k));
    }
}
