package data_structure.unionfind;

import java.util.ArrayList;
import java.util.List;

public class MaximumAssociationSet {
    /**
     * 12/31
     * Union Find Set
     *
     * @param ListA: The relation between ListB's books
     * @param ListB: The relation between ListA's books
     * @return: The answer
     */
    public List<String> maximumAssociationSet(String[] ListA, String[] ListB) {
        if (ListA == null || ListA.length == 0) {
            return new ArrayList<>();
        }

        UnionFindSet<String> ufs = new UnionFindSet<>();
        for (int i = 0; i <= ListA.length - 1; i++) {
            if (!ufs.contains(ListA[i])) {
                ufs.add(ListA[i]);
            }

            if (!ufs.contains(ListB[i])) {
                ufs.add(ListB[i]);
            }

            ufs.union(ListA[i], ListB[i]);
        }

        int max = 0;
        String str = "";

        for (String current : ufs) {
            if (ufs.getNeighborsSize(current) > max) {
                str = current;
                max = ufs.getNeighborsSize(current);
            }
        }

        List<String> list = new ArrayList<>();
        for (String current : ufs) {
            if (ufs.isUnioned(current, str)) {
                list.add(current);
            }
        }

        return list;
    }
}
