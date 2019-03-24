package pointers.window;

public class MinimumSizeSubarraySum {
    /**
     * 1/22/2019
     * Chasing pointers
     *
     * @param nums: an array of integers
     * @param s: An integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = 0;
        int sum = 0, min = Integer.MAX_VALUE;

        while (true) {
            if (sum < s) {
                if (right == nums.length) {
                    break;
                }
                sum += nums[right++];
            } else {
                min = Math.min(min, right - left);
                sum -= nums[left++];
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * 1/22/2019
     * Chasing pointers with coordinate
     *
     * @param nums: an array of integers
     * @param s: An integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize_coordinate(int[] nums, int s) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = 0;
        int sum = nums[0], min = Integer.MAX_VALUE;

        while (true) {
            if (sum < s) {
                right++;
                if (right == nums.length) {
                    break;
                }
                sum += nums[right];
            } else {
                min = Math.min(min, right - left + 1);
                sum -= nums[left++];
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
