package pointers.partition;

import java.util.Arrays;

public class KthLargestElement {
    /**
     * 2/19/2019
     * Partition
     *
     * @param n: An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int n, int[] nums) {
        return traversal(nums, 0, nums.length - 1, nums.length - n);
    }

    private int traversal(int[] nums, int start, int end, int k) {
        System.out.println(Arrays.toString(nums));
        System.out.println("start: " + start + ", end: " + end);
        System.out.println();
        if (start == end) {
            return nums[start];
        }

        int pos = partition(nums, start, end);
        System.out.println(Arrays.toString(nums));

        if (pos > k) {
            return traversal(nums, start, pos - 1, k);
        } else if (pos < k) {
            return traversal(nums, pos + 1, end, k);
        } else {
            return nums[pos];
        }
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

    public static void main(String[] args) {
        int[] nums = {9,3,2,4,8};
        System.out.println(new KthLargestElement().kthLargestElement(3, nums));
    }
}
