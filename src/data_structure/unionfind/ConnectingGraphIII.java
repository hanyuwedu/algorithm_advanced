package data_structure.unionfind;

public class ConnectingGraphIII {
    public class ConnectingGraph3 {
        UnionFindSet<Integer> ufs;

        /*
         * @param n: An integer
         */
        public ConnectingGraph3(int n) {
            this.ufs = new UnionFindSet<>();

            for (int i = 1; i <= n; i++) {
                ufs.add(i);
            }
        }

        /**
         * @param a: An integer
         * @param b: An integer
         * @return: nothing
         */
        public void connect(int a, int b) {
            this.ufs.union(a, b);
        }

        /**
         * @return: An integer
         */
        public int query() {
            return this.ufs.getComponents();
        }
    }
}
