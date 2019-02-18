package data_structure.segmenttree.frequency;

import data_structure.segmenttree.SegmentTreeNode;

public class FrequencySegmentTree {
    private SegmentTreeNode root;

    public void build(int[] A) {
        if (A == null || A.length == 0) {
            this.root = null;
            return;
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i : A) {
            min = Math.min(i, min);
            max = Math.max(i, max);
        }

        this.build(min, max);
        for (int i : A) {
            this.add(i);
        }
    }

    public void build(int min, int max) {
        this.root = buildDivideConquer(min, max);
    }

    private SegmentTreeNode buildDivideConquer(int start, int end) {
        if (start == end) {
            return new SegmentTreeNode(start, end, 0);
        }

        int mid = (start + end) / 2;
        SegmentTreeNode left = buildDivideConquer(start, mid);
        SegmentTreeNode right = buildDivideConquer(mid + 1, end);

        SegmentTreeNode node = new SegmentTreeNode(start, end, 0);
        node.left = left;
        node.right = right;

        return node;
    }

    public void add(int i) {
        add(this.root, i);
    }

    private int add(SegmentTreeNode node, int i) {
        if (node.start > i || node.end < i) {
            return (int) node.val;
        }

        if (node.start == node.end) {
            node.val += 1;
            return (int) node.val;
        }

        int left = add(node.left, i);
        int right = add(node.right, i);
        node.val = left + right;

        return (int) node.val;
    }

    public int search(int min, int max) {
        return search(root, min, max);
    }

    private int search(SegmentTreeNode node, int start, int end) {
        if (node.start > end || node.end < start) {
            return 0;
        }

        if (node.start >= start && node.end <= end) {
            return (int) node.val;
        }

        int left = search(node.left, start, end);
        int right = search(node.right, start, end);

        return left + right;
    }
}
