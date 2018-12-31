package dynamic_programming.interval;

public class LongestPalindromicSubstring {
    /**
     * 12/25/2018
     * Interval DP
     *
     * @param s: input string
     * @return: the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }

        int n = s.length() - 1;
        boolean[][] dp = new boolean[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][i] = true;
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 0 ; i + l <= n; i++) {
                boolean current = s.charAt(i) == s.charAt(i + l);
                if (i + 1 <= i + l - 1) {
                    current &= dp[i + 1][i + l - 1];
                }
                dp[i][i + l] = current;
            }
        }

        int max = 0;
        String result = "";
        for (int i = 0; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (dp[i][j] && j + 1 - i > max) {
                    max = j + 1 - i;
                    result = s.substring(i, j + 1);
                }
            }
        }

        return result;
    }
}
