package dynamic_programming.interval.rotation;

public class MaximalSquare {
    /**
     * 12/25/2018
     * DP with rotation
     *
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length - 1, n = matrix[0].length - 1;
        int[][] dp = new int[2][n + 1];
        dp[0][0] = matrix[0][0];
        int max = dp[0][0];

        for (int j = 1; j <= n; j++) {
            dp[0][j] = matrix[0][j];
            max = Math.max(max, dp[0][j]);
        }

        for (int i = 1; i <= m; i++) {
            dp[i % 2][0] = matrix[i][0];
            max = Math.max(max, dp[i % 2][0]);
            for (int j = 1; j <= n; j++) {
                if (matrix[i][j] == 0) {
                    dp[i % 2][j] = 0;
                    continue;
                }

                dp[i % 2][j] = Math.min(Math.min(dp[(i - 1) % 2][j] + 1, dp[i % 2][j - 1] + 1), dp[(i - 1) % 2][j - 1] + 1);
                max = Math.max(max, dp[i % 2][j]);
            }
        }

        return max * max;
    }
}
