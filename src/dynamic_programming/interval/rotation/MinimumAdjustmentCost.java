package dynamic_programming.interval.rotation;

import java.util.List;

public class MinimumAdjustmentCost {
    private final int MAX_LENGTH = 100;

    /*
     * 12/26
     * DP with rotation
     *
     * @param A: An integer array
     * @param target: An integer
     * @return: An integer
     */
    public int MinAdjustmentCost(List<Integer> A, int target) {
        if (A == null || A.size() < 2) {
            return 0;
        }

        int n = A.size();
        int[][] dp = new int[2][MAX_LENGTH + 1];

        for (int j = 0; j <= MAX_LENGTH; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= MAX_LENGTH; j++) {
                dp[i % 2][j] = Integer.MAX_VALUE;

                for (int k = 0; k <= MAX_LENGTH; k++) {
                    if (dp[(i - 1) % 2][k] == Integer.MAX_VALUE || Math.abs(k - j) > target) {
                        continue;
                    }

                    dp[i % 2][j] = Math.min(dp[i % 2][j], dp[(i - 1) % 2][k] + Math.abs(A.get(i - 1) - j));
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j <= MAX_LENGTH; j++) {
            min = Math.min(min, dp[n % 2][j]);
        }

        return min;
    }
}
