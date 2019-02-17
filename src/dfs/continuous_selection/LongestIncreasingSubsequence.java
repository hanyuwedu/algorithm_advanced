package dfs.continuous_selection;

import java.util.Stack;

public class LongestIncreasingSubsequence {
    /**
     * 2/8/2019
     * DFS on continuous selection
     *
     * @param nums: An integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int[] max = {0};

        dfs(0, nums, stack, max);
        return max[0];
    }

    private void dfs(int i, final int[] nums, Stack<Integer> stack, int[] max) {
        if (i == nums.length) {
            max[0] = Math.max(max[0], stack.size());
            return;
        }

        dfs(i + 1, nums, stack, max);

        if (stack.isEmpty() || stack.peek() < nums[i]) {
            stack.push(nums[i]);
            dfs(i + 1, nums, stack, max);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        int[] nums = {18,85,96,61,49,90,33,68,59,27,0,46,19,63,94,21,57,31,3,62,99,25,23,37,82,11,9,73,35,80,24,69,72,34,77,39,12,88,42,60,55,67,97,45,50,51,65,36,6,20,47,84,58,28,41,93,14,7,78,86,81,13,89,76,30,74,22,2,48,53,43,40,79,64,92,5,75,44,8,66};
        System.out.println(new LongestIncreasingSubsequence().longestIncreasingSubsequence(nums));
    }
}
