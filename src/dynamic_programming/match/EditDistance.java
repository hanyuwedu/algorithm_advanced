package dynamic_programming.match;

public class EditDistance {
    /**
     * 12/30
     * Match DP
     *
     * @param word1: A string
     * @param word2: A string
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0) {
            return word2.length();
        }

        if (word2.length() == 0) {
            return word1.length();
        }

        int m = word1.length(), n = word2.length();
        int[][] dp = new int[2][n + 1];

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            dp[i % 2][0] = i;
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i % 2][j] = Math.min(Math.min(dp[i % 2][j - 1] + 1, dp[(i - 1) % 2][j] + 1), dp[(i - 1) % 2][j - 1]);
                } else {
                    Math.min(Math.min(dp[i % 2][j - 1] + 1, dp[(i - 1) % 2][j] + 1), dp[(i - 1) % 2][j - 1] + 1);
                }
            }
        }

        return  dp[m % 2][n];
    }
}
