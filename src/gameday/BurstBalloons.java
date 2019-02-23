package gameday;


import util.Utilities;

public class BurstBalloons {
    /**
     * 2/22/2019
     * GameDay
     * https://www.lintcode.com/problem/burst-balloons/description
     *
     * @param nums: A list of integer
     * @return: An integer, maximum coins
     */
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[][] max = new int[n][n];

        for (int i = 0; i <= n - 1; i++) {
            int left = i == 0 ? 1 : nums[i - 1];
            int right = i == n - 1 ? 1 : nums[i + 1];

            max[i][i] = left * nums[i] * right;
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 0; i <= n - 1; i++) {
                int j = i + l;
                if (j > n - 1) {
                    break;
                }

                for (int k = i; k <= j; k++) {
                    int left = i == 0 ? 1 : nums[i - 1];
                    int right = j == n - 1 ? 1 : nums[j + 1];

                    int leftMax = k == i ? 0 : max[i][k - 1];
                    int rightMax = k == j ? 0 : max[k + 1][j];

                    int sum = leftMax + rightMax + left * nums[k] * right;
                    max[i][j] = Math.max(max[i][j], sum);
                }
            }
        }

        return max[0][n - 1];
    }



    public static void main(String[] args) {
        int[] balloons = {4,1,5,10};
        System.out.println(new BurstBalloons().maxCoins(balloons));
    }
}
