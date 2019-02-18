package data_structure.hashheap.treemap;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SlidingWindowMedian {
    /**
     * 2/17/2019
     * TreeMap
     *
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || nums.length == 0) {
            return new ArrayList<>();
        }

        // HashHeap<Integer> left = new HashHeap<>((a, b) -> b - a),
        //         right = new HashHeap<>((a, b) -> a - b);

        TreeMap<Integer, Integer> left = new TreeMap<>((a, b) -> b - a),
                right = new TreeMap<>((a, b) -> a - b);

        int[] leftSize = {0}, rightSize = {0};

        // left.add(nums[0]);
        add(left, nums[0], leftSize);

        for (int i = 1; i <= k - 1; i++) {
            if (nums[i] > left.firstKey()) {
                // right.add(nums[i]);
                add(right, nums[i], rightSize);
            } else {
                // left.add(nums[i]);
                add(left, nums[i], leftSize);
            }

            while (leftSize[0] > rightSize[0] + 1) {
                // right.add(left.remove());
                add(right, remove(left, leftSize), rightSize);
            }

            while (leftSize[0] < rightSize[0]) {
                // left.add(right.remove());
                add(left, remove(right, rightSize), leftSize);
            }
        }

        System.out.println("left: " + left);
        System.out.println("right: " + right);
        System.out.println();

        int i = 0, j = k;
        List<Integer> list = new ArrayList<>();

        while (true) {
            // list.add(left.peek());
            list.add(left.firstKey());

            if (j == nums.length) {
                break;
            }

            if (nums[j] <= left.firstKey()) {
                // left.add(nums[j]);
                add(left, nums[j], leftSize);
            } else {
                // right.add(nums[j]);
                add(right, nums[j], rightSize);
            }

            j++;


            // if (!left.remove(nums[i])) {
            if (!remove(left, nums[i], leftSize)) {
                // right.remove(nums[i]);
                remove(right, nums[i], rightSize);
            }

            i++;

//            while (left.size() > right.size() + 1) {
            while (leftSize[0] > rightSize[0] + 1) {
                // right.add(left.remove());
                add(right, remove(left, leftSize), rightSize);
            }

            while (leftSize[0] < rightSize[0]) {
                // left.add(right.remove());
                add(left, remove(right, rightSize), leftSize);
            }

            System.out.println("left: " + left);
            System.out.println("right: " + right);
            System.out.println();
        }

        return list;
    }


    private boolean remove(TreeMap<Integer, Integer> map, int next, int[] size) {
        if (!map.containsKey(next)) {
            return false;
        }

        if (map.get(next) == 1) {
            map.remove(next);
        } else {
            map.put(next, map.get(next) - 1);
        }
        size[0]--;

        return true;
    }

    private int remove(TreeMap<Integer, Integer> map, int[] size) {
        int next = map.firstKey();

        if (map.get(next) == 1) {
            map.remove(next);
        } else {
            map.put(next, map.get(next) - 1);
        }
        size[0]--;

        return next;
    }

    private void add(TreeMap<Integer, Integer> map, int next, int[] size) {
        map.put(next, map.getOrDefault(next, 0) + 1);
        size[0]++;
    }


    public static void main(String[] args) {
        int[] input = {142,38,100,53,22,84,168,50,194,136,111,13,47,45,151,164,126,47,106,124,183,8,87,38,91,121,102,46,82,195,53,18,11,165,61};
        System.out.println(new SlidingWindowMedian().medianSlidingWindow(input, 35));
    }
}
