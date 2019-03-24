package pointers.collide;

public class TrappingRainWater {
    /**
     * 1/9/2019
     * collide pointers
     *
     * @param heights: a list of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length < 3) {
            return 0;
        }

        int left = 0, right = heights.length - 1, water = 0;
        int pivot = 0;

        while (left < right) {
            if (heights[left] < heights[right]) {
                if (heights[left] > pivot) {
                    pivot = heights[left];
                } else {
                    water += pivot - heights[left];
                }
                left++;
            } else {
                if (heights[right] > pivot) {
                    pivot = heights[right];
                } else {
                    water += pivot - heights[right];
                }
                right--;
            }
        }

        return water;
    }
}
