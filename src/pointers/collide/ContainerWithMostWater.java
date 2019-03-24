package pointers.collide;

public class ContainerWithMostWater {
    /**
     * 1/9/2019
     * collide pointers
     *
     * @param heights: a vector of integers
     * @return: an integer
     */
    public int maxArea(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }

        int left = 0, right = heights.length - 1, max = 0;

        while (left < right) {
            int area = Math.min(heights[left], heights[right]) * (right - left);
            max = Math.max(max, area);

            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
