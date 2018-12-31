package gameday;


public class BurstBalloons {
    /**
     * 12/25/2018
     * Interval DP
     *
     * @param nums: A list of integer
     * @return: An integer, maximum coins
     */
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length - 1;
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            int current = nums[i];

            if (i - 1 >= 0) {
                current *= nums[i - 1];
            }

            if (i + 1 <= n) {
                current *= nums[i + 1];
            }

            dp[i][i] = current;
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 0; i + l <= n; i++) {
                dp[i][i + l] = 0;
                for (int k = i; k <= i + l; k++) {
                    int current = nums[k];
                    if (i - 1 >= 0) {
                        current *= nums[i - 1];
                    }

                    if (i + l + 1 <= n) {
                        current *= nums[i + l + 1];
                    }

                    int left = k > i ? dp[i][k - 1] : 0;
                    int right = k < i + l ? dp[k + 1][i + l] : 0;

                    dp[i][i + l] = Math.max(dp[i][i + l], current + left + right);
                }
            }
        }

        return dp[0][n];
    }

    public static void main(String[] args) {
        int[] balloons = {4,1,5,10};
        System.out.println(new BurstBalloons().maxCoins(balloons));
    }
}
