package dynamic_programming.match;

public class InterleavingString {
    /**
     * 12/30
     * Match DP
     *
     * @param s1: A string
     * @param s2: A string
     * @param s3: A string
     * @return: Determine whether s3 is formed by interleaving of s1 and s2
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        int m = s1.length(), n = s2.length();
        boolean[][] dp = new boolean[2][n + 1];

        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= m; i++) {
            dp[i % 2][0] = dp[(i - 1) % 2][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[(i - 1) % 2][j]) {
                    dp[i % 2][j] = true;
                    continue;
                }

                if (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i % 2][j - 1]) {
                    dp[i % 2][j] = true;
                    continue;
                }

                dp[i % 2][j] = false;
            }
        }

        return dp[m % 2][n];
    }
}
