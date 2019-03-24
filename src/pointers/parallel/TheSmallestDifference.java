package pointers.parallel;

import java.util.Arrays;

public class TheSmallestDifference {
    /**
     * 1/20/2019
     * Parallel pointers
     *
     * @param A: An integer array
     * @param B: An integer array
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) {
            return -1;
        }

        Arrays.sort(A);
        Arrays.sort(B);

        int min = Integer.MAX_VALUE;
        int left = 0, right = 0;

        while (left <= A.length - 1 && right <= B.length - 1) {
            min = Math.min(min, Math.abs(A[left] - B[right]));
            if (A[left] < B[right]) {
                left++;
            } else {
                right++;
            }
        }

        return min;
    }
}
