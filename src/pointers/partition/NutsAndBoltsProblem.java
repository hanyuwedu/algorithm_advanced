package pointers.partition;

import java.util.Arrays;

public class NutsAndBoltsProblem {
    /**
     * 2/20/2019
     * Partition
     *
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        if (nuts == null || nuts.length == 0) {
            return;
        }

        traversal(nuts, bolts, 0, nuts.length - 1, compare);
    }

    private void traversal(String[] nuts, String[] bolts, int start, int end, NBComparator compare) {
        if (start >= end) {
            return;
        }

        int p = partition(nuts, bolts[start], start, end, compare);
        partition(bolts, nuts[p], start, end, compare);

        traversal(nuts, bolts, start, p - 1, compare);
        traversal(nuts, bolts, p + 1, end, compare);
    }

    private int partition(String[] strs, String target, int start, int end, NBComparator compare) {
        String pivot = "";

        for (int i = start; i <= end; i++) {
            if (compare.cmp(strs[i], target) == 0 || compare.cmp(target, strs[i]) == 0) {
                pivot = strs[i];
                strs[i] = strs[start];
                strs[start] = pivot;
                break;
            }
        }

        int left = start, right = end;

        while (left < right) {
            while (left < right && (compare.cmp(strs[right], target) == 1 || compare.cmp(target, strs[right]) == -1)) {
                right--;
            }
            strs[left] = strs[right];

            while (left < right && (compare.cmp(strs[left], target) == -1 || compare.cmp(target, strs[left]) == 1)) {
                left++;
            }
            strs[right] = strs[left];
        }
        strs[left] = pivot;

        return left;
    }

    public static class NBComparator {
        public int cmp(String nuts, String bolt) {
            if (!nuts.toLowerCase().equals(nuts) || !bolt.toUpperCase().equals(bolt)) {
                return 2;
            }

            int result = nuts.toLowerCase().compareTo(bolt.toLowerCase());
            if (result > 0) {
                return 1;
            } else if (result < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
