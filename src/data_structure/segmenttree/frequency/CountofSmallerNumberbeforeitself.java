package data_structure.segmenttree.frequency;

import java.util.ArrayList;
import java.util.List;

public class CountofSmallerNumberbeforeitself {
    /**
     * 2/16/2019
     * Frequency Segment Tree
     *
     * @param A: an integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> countOfSmallerNumberII(int[] A) {
        if (A == null || A.length == 0) {
            return new ArrayList<>();
        }

        FrequencySegmentTree tree = new FrequencySegmentTree();

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i : A) {
            min = Math.min(i, min);
            max = Math.max(i, max);
        }

        tree.build(min, max);

        List<Integer> list = new ArrayList<>();
        for (int i : A) {
            tree.add(i);
            list.add(tree.search(Integer.MIN_VALUE, i - 1));
        }

        return list;
    }
}
