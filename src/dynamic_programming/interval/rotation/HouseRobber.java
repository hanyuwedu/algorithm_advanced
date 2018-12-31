package dynamic_programming.interval.rotation;

public class HouseRobber {
    /**
     * 12/25/2018
     * DP with rotation
     *
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        long[] dp = new long[2];

        dp[0] = 0;
        dp[1] = A[0];

        for (int i = 2; i <= n; i++) {
            dp[i % 2] = Math.max(dp[(i - 1) % 2] , dp[(i - 2) % 2] + A[i - 1]);
        }

        return dp[n % 2];
    }
}
