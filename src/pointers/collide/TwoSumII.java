package pointers.collide;

public class TwoSumII {
    /**
     * 1/9/2019
     * collide pointers
     *
     * @param nums: an array of Integer
     * @param target: target = nums[index1] + nums[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(final int[] nums, final int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                int[] result = {left + 1, right + 1};
                return result;
            }
        }

        return new int[0];
    }
}
