package data_structure.segmenttree.frequency;

import java.util.ArrayList;
import java.util.List;

public class CountOfSmallerNumber {
    /**
     * 2/16/2019
     * Frequency Segment tree
     *
     * @param A: An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given integer
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        FrequencySegmentTree tree = new FrequencySegmentTree();
        tree.build(A);

        List<Integer> list = new ArrayList<>();
        for (int i : queries) {
            list.add(tree.search(Integer.MIN_VALUE, i - 1));
        }

        return list;
    }
}
