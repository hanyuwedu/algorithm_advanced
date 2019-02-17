package dfs.continuous_selection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SubsetsII {
    /**
     * 2/7/2019
     * DFS on continuous selection
     *
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public List<List<Integer>> subsetsWithDup_continuous_selection(int[] nums) {
        if (nums == null) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> list = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        dfs_continuous_selection(0, nums, stack, list, visited);
        return list;
    }

    private void dfs_continuous_selection(int i, int[] nums, Stack<Integer> stack, List<List<Integer>> list, boolean[] visited) {
        if (i == nums.length) {
            list.add(new ArrayList<>(stack));
            return;
        }

        dfs_continuous_selection(i + 1, nums, stack, list, visited);

        if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
            return;
        }

        stack.push(nums[i]);
        visited[i] = true;

        dfs_continuous_selection(i + 1, nums, stack, list, visited);

        stack.pop();
        visited[i] = false;
    }



    /**
     * 2/7/2019
     * DFS on discontinuous selection
     *
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public List<List<Integer>> subsetsWithDup_discontinuous_selection(int[] nums) {
        if (nums == null) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> list = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        list.add(new ArrayList<>(stack));

        for (int i = 0; i <= nums.length - 1; i++) {
            dfs_discontinuous_selection(i, nums, stack, list, visited);
        }

        return list;
    }

    private void dfs_discontinuous_selection(int i, int[] nums, Stack<Integer> stack, List<List<Integer>> list, boolean[] visited) {
        if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
            return;
        }

        stack.push(nums[i]);
        visited[i] = true;

        list.add(new ArrayList<>(stack));

        for (int j = i + 1; j <= nums.length - 1; j++) {
            dfs_discontinuous_selection(j, nums, stack, list, visited);
        }

        stack.pop();
        visited[i] = false;
    }
}
