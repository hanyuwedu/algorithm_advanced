package dynamic_programming.nim_game;

public class CoinsinaLineII {
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

        int sum = 0;
        int n = values.length;
        int[] dp = new int[2];

        for (int i = 1; i <= 2; i++) {
            sum += values[n - i];
            dp[i % 2] = sum;
        }

        for (int i = 3; i <= n; i++) {
            sum += values[n - i];
            dp[i % 2] = sum - Math.min(dp[(i - 1) % 2], dp[(i - 2) % 2]);
        }

        return dp[n % 2] > sum / 2;
    }
}
