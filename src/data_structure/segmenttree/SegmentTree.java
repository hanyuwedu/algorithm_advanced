package data_structure.segmenttree;

public class SegmentTree {
    private SegmentTreeNode root;

    public void build(int[] A) {
        if (A == null || A.length == 0) {
            this.root = null;
        } else {
            this.root = divideConquer_build(A, 0, A.length - 1);
        }
    }

    private SegmentTreeNode divideConquer_build(int[] A, int start, int end) {
        /// Base case: 区间长度为1
        if (start == end) {
            return new SegmentTreeNode(start, end, A[start]);
        }

        /// Divide
        SegmentTreeNode left = divideConquer_build(A, start, (start + end) / 2);
        SegmentTreeNode right = divideConquer_build(A, (start + end) / 2 + 1, end);

        /// Conquer
        SegmentTreeNode current = new SegmentTreeNode(start, end, Math.max(left.val, right.val));
        current.left = left;
        current.right = right;

        return current;
    }


    public void modify(int index, int value) {
        divideConquer_modify(root, index, value);
    }

    private long divideConquer_modify(SegmentTreeNode root, int index, int value) {
        /// base base
        /// 完全不包含，不需要后续操作直接返回
        if (root.start > index || root.end < index) {
            return root.val;
        }

        /// 至少包含一部分，需要修改对应值
        if (root.start == root.end) {
            root.val = value;
            return root.val;
        }

        long left = divideConquer_modify(root.left, index, value);
        long right = divideConquer_modify(root.right, index, value);

        root.val = Math.max(left, right);
        return root.val;
    }

    public long queryMax(int start, int end) {
        return divideConquer_queryMax(root, start, end);
    }

    private long divideConquer_queryMax(SegmentTreeNode root, int start, int end) {
        /// Base case: segment tree 的base case不是null
        /// 相离
        if (root.start > end || root.end < start) {
            return 0;
        }

        /// 包含
        if (root.start >= start && root.end <= end) {
            return root.val;
        }

        /// Divide
        long left = divideConquer_queryMax(root.left, start, end);
        long right = divideConquer_queryMax(root.right, start, end);

        /// Conquer
        return Math.max(left, right);
    }
}
