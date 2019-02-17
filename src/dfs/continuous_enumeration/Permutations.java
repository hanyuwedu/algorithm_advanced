package dfs.continuous_enumeration;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Permutations {
    /**
     * DFS
     * 2/9/2019
     *
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) {
            return new ArrayList<>();
        }

        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> list = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        dfs(nums, stack, list, visited);
        return list;
    }

    private void dfs(int[] nums, Stack<Integer> stack, List<List<Integer>> list, boolean[] visited) {
        if (nums.length == stack.size()) {
            list.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i <= nums.length - 1; i++) {
            if (visited[i]) {
                continue;
            }

            stack.push(nums[i]);
            visited[i] = true;

            dfs(nums, stack, list, visited);

            visited[i] = false;
            stack.pop();
        }
    }
}
