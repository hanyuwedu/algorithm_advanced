package data_structure.segmenttree;

import util.Interval;

import java.util.ArrayList;
import java.util.List;

public class IntervalSum {
    /**
     * 2/16/2019
     * Segment Tree
     *
     * @param A: An integer list
     * @param queries: An query list
     * @return: The result list
     */
    public List<Long> intervalSum(int[] A, List<Interval> queries) {
        if (A == null || A.length == 0) {
            return new ArrayList<>();
        }

        List<Long> list = new ArrayList<>();
        SegmentTree tree = new SegmentTree(A);

        for (Interval query : queries) {
            long sum = tree.querySum(query.start, query.end);
            list.add(sum);
        }

        return list;
    }

    public class SegmentTree {
        private SegmentTreeNode root;

        public SegmentTree(int[] A) {
            this.root = buildTree(0, A.length - 1, A);
        }

        private SegmentTreeNode buildTree(int start, int end, int[] A) {
            if (start == end) {
                SegmentTreeNode node = new SegmentTreeNode(start, end, A[start]);
                return node;
            }


            SegmentTreeNode left = buildTree(start, (start + end) / 2, A);
            SegmentTreeNode right = buildTree((start + end) / 2 + 1, end, A);

            SegmentTreeNode current = new SegmentTreeNode(start, end, left.val + right.val);
            current.left = left;
            current.right = right;

            return current;
        }

        public long querySum(int start, int end) {
            return divideConquer(root, start, end);
        }

        private long divideConquer(SegmentTreeNode root, int start, int end) {
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
            long left = divideConquer(root.left, start, end);
            long right = divideConquer(root.right, start, end);

            /// Conquer
            return left + right;
        }
    }

    public class SegmentTreeNode {
        public SegmentTreeNode left, right;
        public int start, end;
        public long val;

        public SegmentTreeNode(int start, int end, long val) {
            this.start = start;
            this.end = end;
            this.val = val;
        }
    }
}
