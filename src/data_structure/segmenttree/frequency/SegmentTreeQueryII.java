package data_structure.segmenttree.frequency;

import data_structure.segmenttree.SegmentTreeNode;

public class SegmentTreeQueryII {
    /**
     * 2/16/2019
     *
     * @param root: The root of segment tree.
     * @param start: start value.
     * @param end: end value.
     * @return: The count number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        if (root == null || root.start > end || root.end < start) {
            return 0;
        }

        if (root.start >= start && root.end <= end) {
            return (int) root.val;
        }

        return query(root.left, start, end) + query(root.right, start, end);
    }
}
