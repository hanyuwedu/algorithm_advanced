package dynamic_programming.interval;

public class ScrambleString {
    /**
     * 12/25/2018
     * Interval DP
     *
     * @param s1: A string
     * @param s2: Another string
     * @return: whether s2 is a scrambled string of s1
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        if (s1.length() == 0) {
            return true;
        }

        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];

        for (int i = 0; i <= n - 1; i++) {
            for (int j = 0; j <= n - 1; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - 1; i++) {
                for (int j = 0; j <= n - 1; j++) {
                    if (i + l > n || j + l > n) {
                        dp[i][j][l] = false;
                        break;
                    }

                    for (int d = 1; d <= l; d++) {
                        if (dp[i][j][d] && dp[i + d][j + d][l - d]) {
                            dp[i][j][l] = true;
                            break;
                        }

                        if (dp[i][j + l - d][d] && dp[i + d][j][l - d]) {
                            dp[i][j][l] = true;
                            break;
                        }
                    }
                }
            }
        }

        return dp[0][0][n];
    }
}
