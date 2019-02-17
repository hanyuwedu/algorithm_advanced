package dfs.continuous_enumeration;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Factorization {
    /**
     * 2/6/2019
     * DFS on simple structure
     *
     * @param n: An integer
     * @return: a list of combination
     */
    public List<List<Integer>> getFactors(int n) {
        if (n <= 1) {
            return new ArrayList<>();
        }

        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> list = new ArrayList<>();
        dfs(stack, list, n);

        return list;
    }

    private void dfs(Stack<Integer> stack, List<List<Integer>> list, int n) {
        if (n == 1) {
            if (stack.size() > 1) {
                list.add(new ArrayList<>(stack));
            }

            return;
        }

        for (int i = stack.isEmpty() ? 2 : stack.peek(); i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                stack.push(i);
                dfs(stack, list, n / i);
                stack.pop();
            }
        }

        stack.push(n);
        dfs(stack, list, 1);
        stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(new Factorization().getFactors(2147483647));
    }
}
