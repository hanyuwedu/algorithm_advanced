package pointers.maxsidevalue;

public class TrappingRainWater {
    /**
     * 1/9/2019
     * max side value
     *
     * @param heights: a list of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length < 3) {
            return 0;
        }

        int n = heights.length - 1;
        int[] left = new int[n + 1], right = new int[n + 1];

        int max = 0;
        for (int i = 0; i <= n; i++) {
            max = Math.max(max, heights[i]);
            left[i] = max;
        }

        max = 0;
        for (int i = n; i >= 0; i--) {
            max = Math.max(max, heights[i]);
            right[i] = max;
        }

        int water = 0;
        for (int i = 0; i <= n; i++) {
            water += Math.min(left[i], right[i]) - heights[i];
        }

        return water;
    }
}
