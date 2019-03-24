package pointers.collide;

import java.util.Arrays;

public class TriangleCount {
    /**
     * 1/9/2018
     * collide pointers
     *
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int[] S) {
        if (S == null || S.length < 3) {
            return 0;
        }

        Arrays.sort(S);
        int count = 0;

        for (int c = S.length - 1; c >= 0; c--) {
            int a = 0, b = c - 1;
            while (a < b) {
                int sum = S[a] + S[b];
                if (sum > S[c]) {
                    count += b - a;
                    b--;
                } else {
                    a++;
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
