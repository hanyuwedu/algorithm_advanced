package data_structure.segmenttree;

import data_structure.segmenttree.frequency.FrequencySegmentTree;

public class Test {
    public static void main(String[] args) {
        int[] input = {1,2,3,4,5,6};
        FrequencySegmentTree tree = new FrequencySegmentTree();
        tree.build(input);

        System.out.println(tree.search(0, 1));
    }
}
