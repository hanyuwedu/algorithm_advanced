package data_structure.unionfind;

/**
 * 12/31
 * UnionFindSet
 */
public class ConnectingGraphII {
    public class ConnectingGraph2 {
        UnionFindSet<Integer> ufs;

        /*
         * @param n: An integer
         */
        public ConnectingGraph2(int n) {
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
         * @return: An integer
         */
        public int query(int a) {
            return this.ufs.getNeighborsSize(a);
        }
    }
}
