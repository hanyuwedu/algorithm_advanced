package gameday;

public class KSum {
    /**
     * 2/22/2019
     * GameDay
     * https://www.lintcode.com/problem/k-sum/description
     *
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int a = A.length;
        int[][][] dp = new int[a + 1][k + 1][target + 1];

        dp[0][0][0] = 1;
        for (int i = 1; i <= a; i++) {
            dp[i][0][0] = 1;
            for (int j = 1; j <= k; j++) {
                for (int x = 1; x <= target; x++) {
                    if (x - A[i - 1] < 0) {
                        dp[i][j][x] = dp[i - 1][j][x];
                        continue;
                    }

                    dp[i][j][x] = dp[i - 1][j - 1][x - A[i - 1]] + dp[i - 1][j][x];
                }
            }
        }

        return dp[a][k][target];
    }
}
