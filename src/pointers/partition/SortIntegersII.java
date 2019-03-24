package pointers.partition;

public class SortIntegersII {
    /**
     * 2/19/2019
     * Partition sort
     *
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        traversal(A, 0, A.length - 1);
    }

    private void traversal(int[] A, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = partition(A, start, end);
        traversal(A, start, mid - 1);
        traversal(A, mid + 1, end);
    }

    private int partition(int[] A, int start, int end) {
        int mid = (start + end) / 2;
        int temp = A[mid];
        A[mid] = A[start];
        A[start] = temp;

        int left = start, right = end, pivot = A[left];

        while (left < right) {
            while (left < right && A[right] >= pivot) {
                right--;
            }

            A[left] = A[right];

            while (left < right && A[left] <= pivot) {
                left++;
            }

            A[right] = A[left];
        }

        A[left] = pivot;

        return left;
    }
}
