package dynamic_programming.nim_game;

public class CoinsinaLine {
    /**
     * 12/26
     * nim game DP
     *
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        if (n == 0) {
            return false;
        }

        boolean[] dp = new boolean[2];
        dp[1 % 2] = dp[2 % 2] = true;

        for (int i = 3; i <= n; i++) {
            dp[i % 2] = (!dp[(i - 1) % 2] || !dp[(i - 2) % 2]);
        }

        return dp[n % 2];
    }
}
