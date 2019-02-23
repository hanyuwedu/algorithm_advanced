package gameday;

public class CoinsinaLineIII {
    /**
     * 2/22/2019
     * GameDay
     * https://www.lintcode.com/problem/coins-in-a-line-iii/description
     *
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }

        int n = values.length;
        int[][] sum = new int[n][n];

        for (int i = 0; i <= n - 1; i++) {
            sum[i][i] = values[i];
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 0; i + l <= n - 1; i++) {
                int j = i + l;
                sum[i][j] = sum[i][j - 1] + values[j];
            }
        }

        int[][] dp = new int[n][n];
        for (int i = 0; i <= n - 1; i++) {
            dp[i][i] = values[i];
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 0; i + l <= n - 1; i++) {
                int j = i + l;
                dp[i][j] = Math.max(sum[i][j] - dp[i + 1][j], sum[i][j] - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1] > sum[0][n - 1] / 2;
    }
}
