package data_structure.segmenttree;

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
