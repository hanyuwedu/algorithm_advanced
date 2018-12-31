package dynamic_programming.memorized_search;

import java.util.HashMap;
import java.util.Map;

public class BurstBalloons {
    /**
     * 12/27
     * Memorized search
     *
     * @param nums: A list of integer
     * @return: An integer, maximum coins
     */
    public int maxCoins(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> dp = new HashMap<>();

        return search(0, nums.length - 1, nums, dp);
    }

    private int search(int left, int right, int[] nums, Map<Integer, Integer> dp) {
        if (left == right) {
            int current = nums[left];
            if (left > 0) {
                current *= nums[left - 1];
            }

            if (right < nums.length - 1) {
                current *= nums[right + 1];
            }

            dp.put(left * nums.length + right, current);
        }

        int key = left * nums.length + right;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int value = 0;
        for (int k = left; k <= right; k++) {
            int current = nums[k];
            if (left > 0) {
                current *= nums[left - 1];
            }

            if (right < nums.length - 1) {
                current *= nums[right + 1];
            }

            if (k > left) {
                current += search(left, k - 1, nums, dp);
            }

            if (k < right) {
                current += search(k + 1, right, nums, dp);
            }

            value = Math.max(value, current);
        }

        dp.put(key, value);
        return value;
    }
}
