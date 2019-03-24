package pointers.parallel;

import java.util.LinkedList;
import java.util.Queue;

public class MergeKSortedArrays {
    /**
     * 1/20/2019
     * Parallel pointers
     *
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null || arrays.length == 0) {
            return new int[0];
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int[] array : arrays) {
            if (array != null) {
                queue.add(array);
            }
        }

        while (queue.size() > 1) {
            int[] a = queue.remove(), b = queue.remove();
            int[] c = merge(a, b);
            queue.add(c);
        }

        return queue.peek();
    }

    private int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int left = 0, right = 0, current = 0;

        while (left < a.length || right < b.length) {
            if (left == a.length) {
                c[current++] = b[right++];
            } else if (right == b.length) {
                c[current++] = a[left++];
            } else {
                if (a[left] < b[right]) {
                    c[current++] = a[left++];
                } else {
                    c[current++] = b[right++];
                }
            }
        }

        return c;
    }
}
