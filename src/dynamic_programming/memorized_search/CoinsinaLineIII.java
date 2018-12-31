package dynamic_programming.memorized_search;

import java.util.HashMap;
import java.util.Map;

public class CoinsinaLineIII {
    /**
     * 12/27
     * Memorized Search
     *
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }

        if (values.length <= 2) {
            return true;
        }

        int n = values.length - 1;
        int[][] sum = new int[n + 1][n + 1];


        for (int i = 0; i <= n; i++) {
            sum[i][i] = values[i];
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 0; i + l <= n; i++) {
                sum[i][i + l] = sum[i][i + l - 1] + values[i + l];
            }
        }

        Map<Integer, Integer> dp = new HashMap<>();

        return search(0, n, dp, sum) > sum[0][n] / 2;
    }

    private int search(int left, int right, Map<Integer, Integer> dp, int[][] sum) {
        if (left == right) {
            return sum[left][right];
        }

        int key = left * sum.length + right;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int value = sum[left][right] - Math.min(search(left + 1, right, dp, sum), search(left, right - 1, dp, sum));

        dp.put(key, value);
        return value;
    }
}
