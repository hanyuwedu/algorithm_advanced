package dynamic_programming.match;

public class DistinctSubsequences {
    /**
     * 12/30
     * Match DP
     *
     * @param : A string
     * @param : A string
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
        if (S.length() < T.length()) {
            return 0;
        }

        int s = S.length(), t = T.length();
        int[][] dp = new int[2][t + 1];

        for (int j = 0; j <= t; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i <= s; i++) {
            dp[i % 2][0] = 0;
            for (int j = 1; j <= t; j++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[i % 2][j] = dp[(i - 1) % 2][j] + dp[(i - 1) % 2][j - 1];
                } else {
                    dp[i % 2][j] = dp[(i - 1) % 2][j];
                }
            }
        }

        return dp[s % 2][t];
    }
}
