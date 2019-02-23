package gameday;

import java.util.Arrays;

public class TriangleCount {
    /**
     * 2/23/2019
     * GameDay
     * https://www.lintcode.com/problem/triangle-count/description
     *
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int[] S) {
        if (S == null || S.length < 3) {
            return 0;
        }

        int count = 0;
        Arrays.sort(S);

        for (int c = S.length - 1; c >= 0; c--) {
            int a = 0, b = c - 1;
            while (a < b) {
                if (S[a] + S[b] <= S[c]) {
                    a++;
                } else {
                    count += b - a;
                    b--;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] input = {3,4,6,7};
        System.out.println(new TriangleCount().triangleCount(input));
    }
}
