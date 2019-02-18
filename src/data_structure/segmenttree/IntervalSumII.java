package data_structure.segmenttree;

public class IntervalSumII {

    SegmentTree tree;

    /*
     * @param A: An integer array
     */
    public IntervalSumII(int[] A) {
        this.tree = new SegmentTree(A);
    }

    /*
     * @param start: An integer
     * @param end: An integer
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        return this.tree.querySum(start, end);
    }

    /*
     * @param index: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void modify(int index, int value) {
        this.tree.modify(index, value);
    }


    public class SegmentTree {
        private SegmentTreeNode root;

        public SegmentTree(int[] A) {
            if (A == null || A.length == 0) {
                return;
            }
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

        public void modify(int index, int value) {
            divideConquer_modify(index, value, this.root);
        }

        private long divideConquer_modify(int index, int value, SegmentTreeNode node) {
            if (node.start > index || node.end < index) {
                return node.val;
            }

            if (node.start == node.end) {
                node.val = value;
                return value;
            }

            long left = divideConquer_modify(index, value, node.left);
            long right = divideConquer_modify(index, value, node.right);

            node.val = left + right;
            return node.val;
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
