package dynamic_programming.memorized_search;

import java.util.HashMap;
import java.util.Map;

public class StoneGame {
    /**
     * 12/27/2018
     * Memorized Search
     *
     * @param A: An integer array
     * @return: An integer
     */
    public int stoneGame(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length - 1;
        int[][] sum = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            sum[i][i] = A[i];
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 0; i + l <= n; i++) {
                sum[i][i + l] = sum[i][i + l - 1] + A[i + l];
            }
        }

        Map<Integer, Integer> dp = new HashMap<>();
        return search(0, n, sum, dp);
    }

    private int search(int left, int right, int[][] sum, Map<Integer, Integer> dp) {
        if (left == right) {
            return 0;
        }

        int key = left * sum.length + right;

        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int value = Integer.MAX_VALUE;
        for (int mid = left; mid < right; mid++) {
            value = Math.min(value, sum[left][right] + search(left, mid, sum, dp) + search(mid + 1, right, sum, dp));
        }

        dp.put(key, value);
        return value;
    }
}
