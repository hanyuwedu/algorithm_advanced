package data_structure.segmenttree.index;

import data_structure.segmenttree.SegmentTreeNode;
import util.TreeNode;

public class MaxTree {
    /**
     * 2/16/2019
     * Segment Tree
     *
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }

        SegmentTree tree = new SegmentTree();
        tree.build(A);


        return maxTree(A, 0, A.length - 1, tree);
    }

    private TreeNode maxTree(int[] A, int start, int end, SegmentTree tree) {
        if (start > end) {
            return null;
        }

        int max = tree.search(start, end);
        TreeNode left = maxTree(A, start, max - 1, tree);
        TreeNode right = maxTree(A, max + 1, end, tree);

        TreeNode node = new TreeNode(A[max]);
        node.left = left;
        node.right = right;

        return node;
    }

    public class SegmentTree {
        private SegmentTreeNode root;
        int[] A;

        public void build(int[] A) {
            if (A == null || A.length == 0) {
                this.root = null;
            } else {
                this.A = A;
                this.root = build(0, A.length - 1);
            }
        }

        private SegmentTreeNode build(int start, int end) {
            if (start == end) {
                return new SegmentTreeNode(start, end, start);
            }

            int mid = (start + end) / 2;
            SegmentTreeNode left = build(start, mid);
            SegmentTreeNode right = build(mid + 1, end);

            long max = A[(int) left.val] > A[(int) right.val] ? left.val : right.val;
            SegmentTreeNode current = new SegmentTreeNode(start, end, max);
            current.left = left;
            current.right = right;

            return current;
        }

        public int search(int start, int end) {
            return search(start, end, this.root);
        }

        private int search(int start, int end, SegmentTreeNode node) {
            if (node.start > end || node.end < start) {
                return -1;
            }

            if (node.start >= start && node.end <= end) {
                return (int) node.val;
            }

            int left = search(start, end, node.left);
            int right = search(start, end, node.right);

            if (left == -1) {
                return right;
            } else if (right == -1) {
                return left;
            } else if (A[left] > A[right]) {
                return left;
            } else {
                return right;
            }
        }
    }

    public static void main(String[] args) {
        int[] input = {2,5,6,0,3,1};

        TreeNode node = new MaxTree().maxTree(input);
    }
}
