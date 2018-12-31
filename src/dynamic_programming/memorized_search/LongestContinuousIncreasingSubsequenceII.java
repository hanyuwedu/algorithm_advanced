package dynamic_programming.memorized_search;

import java.util.HashMap;
import java.util.Map;

public class LongestContinuousIncreasingSubsequenceII {
    /**
     * 12/27
     * memorized search
     *
     * @param matrix: A 2D-array of integers
     * @return: an integer
     */
    public int longestContinuousIncreasingSubsequence2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        Map<Integer, Integer> dp = new HashMap<>();
        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
        int max = 0;

        for (int i = 0; i <= matrix.length - 1; i++) {
            for (int j = 0; j <= matrix[0].length - 1; j++) {
                max = Math.max(max, search(i, j, dx, dy, matrix, dp));
            }
        }

        return max;
    }

    private int search(int i, int j, int[] dx, int[] dy, int[][] matrix, Map<Integer, Integer> dp) {
        int m = matrix.length, n = matrix[0].length;

        int key = i * n + j;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int value = 1;
        for (int d = 0; d <= 3; d++) {
            int x = i + dx[d], y = j + dy[d];
            if (x < 0 || x >= m || y < 0 || y >= n) {
                continue;
            }

            if (matrix[x][y] < matrix[i][j]) {
                value = Math.max(value, search(x, y, dx, dy, matrix, dp) + 1);
            }
        }

        dp.put(key, value);
        return value;
    }
}
