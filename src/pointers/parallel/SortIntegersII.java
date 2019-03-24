package pointers.parallel;

public class SortIntegersII {
    /**
     * 1/20/2019
     * Merge sort with parralell pointers
     *
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] A) {
        if (A == null || A.length < 2) {
            return;
        }

        mergeSort(A, 0, A.length - 1);
    }

    private void mergeSort(int[] A, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(A, start, mid);
        mergeSort(A, mid + 1, end);

        int[] c = new int[end - start + 1];
        int left = start, right = mid + 1, current = 0;

        while (left < mid + 1 || right < end + 1) {
            if (left == mid + 1) {
                c[current++] = A[right++];
            } else if (right == end + 1) {
                c[current++] = A[left++];
            } else {
                if (A[left] < A[right]) {
                    c[current++] = A[left++];
                } else {
                    c[current++] = A[right++];
                }
            }
        }

        for (int i = 0; i <= c.length - 1; i++) {
            A[start + i] = c[i];
        }
    }
}
