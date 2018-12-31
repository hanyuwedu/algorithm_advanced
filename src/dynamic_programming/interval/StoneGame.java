package dynamic_programming.interval;

public class StoneGame {
    /**
     * 12/25/2018
     * Interval DP
     *
     * @param A: An integer array
     * @return: An integer
     */
    public int stoneGame(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length - 1;
        int[][] dp = new int[n + 1][n + 1];
        int[][] sum = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            sum[i][i] = A[i];
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 0; i + l <= n; i++) {
                sum[i][i + l] = sum[i][i + l - 1] + A[i + l];
            }
        }

        for (int i = 0; i <= n; i++) {
            dp[i][i] = 0;
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 0; i + l <= n; i++) {
                dp[i][i + l] = Integer.MAX_VALUE;
                for (int k = i; k < i + l; k++) {
                    dp[i][i + l] = Math.min(dp[i][i + l], sum[i][i + l] + dp[i][k] + dp[k + 1][i + l]);
                }
            }
        }

        return dp[0][n];
    }

    public static void main(String[] args) {
        int[] stone = {3,4,3};
        System.out.println(new StoneGame().stoneGame(stone));
    }
}
