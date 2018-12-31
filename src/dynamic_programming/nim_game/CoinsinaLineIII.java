package dynamic_programming.nim_game;

public class CoinsinaLineIII {
    /**
     * 12/26
     * nim game DP
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
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            sum[i][i] = values[i];
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 0; i + l <= n; i++) {
                sum[i][i + l] = sum[i][i + l - 1] + values[i + l];
            }
        }

        for (int i = 0; i <= n; i++) {
            dp[i][i] = values[i];
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 0; i + l <= n; i++) {
                dp[i][i + l] = sum[i][i + l] - Math.min(dp[i + 1][i + l], dp[i][i + l - 1]);
            }
        }

        return dp[0][n] > sum[0][n] / 2;
    }
}
