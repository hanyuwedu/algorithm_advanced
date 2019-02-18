package data_structure.unionfind;

public class SetUnion {
    /**
     * 12/31
     * Union Find Set
     *
     * @param sets: Initial set list
     * @return: The final number of sets
     */
    public int setUnion(int[][] sets) {
        if (sets == null || sets.length == 0) {
            return 0;
        }

        UnionFindSet<Integer> ufs = new UnionFindSet<>();
        for (int[] set : sets) {
            if (set.length == 0) {
                continue;
            }

            int root = set[0];
            for (int i : set) {
                if (!ufs.contains(i)) {
                    ufs.add(i);
                }

                ufs.union(i, root);
            }
        }

        return ufs.getComponents();
    }
}
