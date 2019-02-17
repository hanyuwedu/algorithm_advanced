package dfs.continuous_selection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Subsets {
    /**
     * 2/6/2019
     * DFS on discontinuous selection
     *
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets_discontinuous_selection(int[] nums) {
        if (nums == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        Arrays.sort(nums);

        list.add(new ArrayList<>(stack));

        for (int i = 0; i <= nums.length - 1; i++) {
            dfs_discontinuous_selection(list, stack, i, nums);
        }

        return list;
    }

    private void dfs_discontinuous_selection(List<List<Integer>> list, Stack<Integer> stack, int i, int[] nums) {
        stack.push(nums[i]);
        list.add(new ArrayList<>(stack));

        for (int j = i + 1; j <= nums.length - 1; j++) {
            dfs_discontinuous_selection(list, stack, j, nums);
        }

        stack.pop();
    }



    /**
     * 2/6/2019
     * DFS on continuous selection
     *
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets_continuous_selection(int[] nums) {
        if (nums == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        Arrays.sort(nums);
        dfs_continuous_selection(list, stack, 0, nums);

        return list;
    }

    private void dfs_continuous_selection(List<List<Integer>> list, Stack<Integer> stack, int i, int[] nums) {
        if (i == nums.length) {
            list.add(new ArrayList<>(stack));
            return;
        }

        stack.push(nums[i]);
        dfs_continuous_selection(list, stack, i + 1, nums);
        stack.pop();
        dfs_continuous_selection(list, stack, i + 1, nums);
    }
}
