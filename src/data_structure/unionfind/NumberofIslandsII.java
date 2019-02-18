package data_structure.unionfind;

import util.Point;

import java.util.ArrayList;
import java.util.List;

public class NumberofIslandsII {
    /**
     * 12/31
     * Union Find Set
     *
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        if (operators == null || operators.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> list = new ArrayList<>();
        UnionFindSet<Integer> ufs = new UnionFindSet<>();

        int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

        for (Point current : operators) {
            int x = current.x, y = current.y;
            if (x < 0 || x > n - 1 || y < 0 || y > m - 1) {
                list.add(ufs.getComponents());
                continue;
            }

            ufs.add(x * m + y);
            for (int d = 0; d <= 3; d++) {
                int i = x + dx[d], j = y + dy[d];
                if (i >= 0 && i <= n - 1 && j >= 0 && j <= m - 1) {
                    if (ufs.contains(i * m + j)) {
                        ufs.union(x * m + y, i * m + j);
                    }
                }
            }

            list.add(ufs.getComponents());
        }

        return list;
    }
}
