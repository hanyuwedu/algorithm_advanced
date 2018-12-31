package dynamic_programming.backpack;

public class BackpackIV {
    /**
     * 12/26
     * Backpack DP
     *
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackIV(int[] nums, int target) {
        if (target <= 0 || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[][] dp = new int[2][target + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < nums[i - 1]) {
                    dp[i % 2][j] = dp[(i - 1) % 2][j];
                    continue;
                }

                dp[i % 2][j] = dp[(i - 1) % 2][j] + dp[i % 2][j - nums[i - 1]];
            }
        }

        return dp[n % 2][target];
    }

    public static void main(String[] args) {
        int[] pack = {2,3,6,7};
        int vol = 7;

        System.out.println(new BackpackIV().backPackIV(pack, vol));
    }
}
