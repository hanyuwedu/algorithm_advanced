package data_structure.unionfind;

/**
 * 12/31/2018
 * Union Find Set
 */
public class ConnectingGraph {
    UnionFindSet<Integer> ufs;

    /*
     * @param n: An integer
     */public ConnectingGraph(int n) {
        this.ufs = new UnionFindSet<>();

        for (int i = 1; i <= n; i++) {
            ufs.add(i);
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        this.ufs.union(a, b);
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: A boolean
     */
    public boolean query(int a, int b) {
        return this.ufs.isUnioned(a, b);
    }
}
