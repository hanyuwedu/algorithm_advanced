package data_structure.segmenttree;

import util.Interval;

import java.util.ArrayList;
import java.util.List;

public class IntervalMinimumNumber {
    /**
     * 2/16/2019
     * Segment Tree
     *
     * @param A: An integer array
     * @param queries: An query list
     * @return: The result list
     */
    public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
        if (A == null || A.length == 0) {
            return new ArrayList<>();
        }

        SegmentTree tree = new SegmentTree();
        tree.build(A);

        List<Integer> list = new ArrayList<>();

        for (Interval interval : queries) {
            list.add((int) (tree.queryMin(interval.start, interval.end)));
        }

        return list;
    }

    public class SegmentTree {
        private SegmentTreeNode root;

        public void build(int[] A) {
            if (A == null || A.length == 0) {
                this.root = null;
            }

            this.root = divideConquer_build(A, 0, A.length - 1);
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
            SegmentTreeNode current = new SegmentTreeNode(start, end, Math.min(left.val, right.val));
            current.left = left;
            current.right = right;

            return current;
        }


        public void modify(SegmentTreeNode root, int index, int value) {
            divideConquer_modify(root, index, value);
        }

        private long divideConquer_modify(SegmentTreeNode root, int index, int value) {
            /// 完全不包含，不需要后续操作直接返回
            if (root.start > index || root.end < index) {
                return root.val;
            }

            /// 至少包含一部分，需要修改对应值

            /// base base
            if (root.start == root.end) {
                root.val = value;
                return root.val;
            }

            long left = divideConquer_modify(root.left, index, value);
            long right = divideConquer_modify(root.right, index, value);

            root.val = Math.max(left, right);
            return root.val;
        }

        public long queryMin(int start, int end) {
            return divideConquer_queryMin(root, start, end);
        }

        private long divideConquer_queryMin(SegmentTreeNode root, int start, int end) {
            /// Base case: segment tree 的base case不是null
            /// 相离
            if (root.start > end || root.end < start) {
                return Integer.MAX_VALUE;
            }

            /// 包含
            if (root.start >= start && root.end <= end) {
                return root.val;
            }

            /// Divide
            long left = divideConquer_queryMin(root.left, start, end);
            long right = divideConquer_queryMin(root.right, start, end);

            /// Conquer
            return Math.min(left, right);
        }
    }

    public class SegmentTreeNode {
        public int start, end;
        public SegmentTreeNode left, right;
        public long val;

        public SegmentTreeNode(int start, int end, long val) {
            this.start = start;
            this.end = end;
            this.val = val;
        }
    }
}
