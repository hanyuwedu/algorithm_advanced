package gameday;

public class KSum {
    /**
     * 12/26
     * Backpack DP
     *
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        if (target <= 0 || k == 0 || target == 0) {
            return 0;
        }

        int a = A.length;
        int[][][] dp = new int[2][k + 1][target + 1];
        dp[0][0][0] = 1;

        for (int i = 1; i <= a; i++) {
            dp[i % 2][0][0] = 1;
            for (int j = 1; j <= k; j++) {
                for (int t = 1; t <= target; t++) {

                    if (A[i - 1] > t) {
                        dp[i % 2][j][t] = dp[(i - 1) % 2][j][t];
                        continue;
                    }

                    dp[i % 2][j][t] = dp[(i - 1) % 2][j - 1][t - A[i - 1]] + dp[(i - 1) % 2][j][t];
                }
            }
        }

        return dp[a % 2][k][target];
    }
}
