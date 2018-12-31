package dynamic_programming.match;

public class LongestCommonSubsequence {
    /**
     * 12/30
     * Match DP
     *
     * @param A: A string
     * @param B: A string
     * @return: The length of longest common subsequence of A and B
     */
    public int longestCommonSubsequence(String A, String B) {
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return 0;
        }

        int a = A.length(), b = B.length();

        int[][] dp = new int[2][b + 1];

        for (int j = 0; j <= b; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= a; i++) {
            dp[i % 2][0] = 0;
            for (int j = 1; j <= b; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i % 2][j] = Math.max(Math.max(dp[i % 2][j - 1], dp[(i - 1) % 2][j]), dp[(i - 1) % 2][j - 1] + 1);
                } else {
                    dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[i % 2][j - 1]);
                }
            }
        }

        return dp[a % 2][b];
    }
}
