package gameday;

public class NutsAndBoltsProblem {
    /**
     * 2/23/2019
     * GameDay
     * https://www.lintcode.com/problem/nuts-bolts-problem/description
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

        sort(nuts, bolts, 0, nuts.length - 1, compare);
    }

    private void sort(String[] nuts, String[] bolts, int start, int end, NBComparator compare) {
        if (start >= end) {
            return;
        }

        int mid = partition(nuts, start, end, bolts[start], compare);
        partition(bolts, start, end, nuts[mid], compare);

        sort(nuts, bolts, start, mid - 1, compare);
        sort(nuts, bolts, mid + 1, end, compare);
    }

    private int partition(String[] strs, int start, int end, String pivot, NBComparator compare) {
        String first = "";
        for (int i = start; i <= end; i++) {
            if (compare.cmp(strs[i], pivot) == 0 || compare.cmp(pivot, strs[i]) == 0) {
                first = strs[i];
                strs[i] = strs[start];
                strs[start] = first;
            }
        }

        int left = start, right = end;
        while (left < right) {
            while (left < right && (compare.cmp(strs[right], pivot) == 1 || compare.cmp(pivot, strs[right]) == -1)) {
                right--;
            }
            strs[left] = strs[right];

            while (left < right && (compare.cmp(strs[left], pivot) == -1 || compare.cmp(pivot, strs[left]) == 1)) {
                left++;
            }
            strs[right] = strs[left];
        }
        strs[left] = first;

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
