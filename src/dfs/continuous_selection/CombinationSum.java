package dfs.continuous_selection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CombinationSum {
    /**
     * 2/7/2019
     * DFS on continous selection
     *
     * @param candidates: A list of integers
     * @param target: An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(candidates);
        boolean[] visited = new boolean[candidates.length];
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> list = new ArrayList<>();

        dfs(stack, list, candidates, 0, visited, target, 0);
        return list;
    }

    private void dfs(Stack<Integer> stack, List<List<Integer>> list, int[] nums, int i, boolean[] visited, int target, int sum) {
        if (sum == target) {
            list.add(new ArrayList<>(stack));
        }

        if (sum >= target) {
            return;
        }

        if (i >= nums.length) {
            return;
        }

        boolean temp = visited[i];
        visited[i] = false;

        dfs(stack, list, nums, i + 1, visited, target, sum);

        if (i > 0 && !visited[i - 1] && nums[i] == nums[i - 1]) {
            return;
        }

        visited[i] = true;
        stack.push(nums[i]);
        dfs(stack, list, nums, i, visited, target, sum + nums[i]);

        visited[i] = temp;
        stack.pop();
    }
}
