package pointers.window;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringwithAtMostKDistinctCharacters {
    /**
     * 1/23/2019
     * Chasing pointers
     *
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0, right = 0;
        Map<Character, Integer> visited = new HashMap<>();
        int max = 0;

        while (true) {
            if (visited.size() <= k) {
                max = Math.max(max, right - left);
                if (right == s.length()) {
                    break;
                }
                visited.put(s.charAt(right), visited.getOrDefault(s.charAt(right), 0) + 1);
                right++;
            } else {
//                visited.put(s.charAt(left), visited.get(s.charAt(left) - 1)); 很有意思的错误
                visited.put(s.charAt(left), visited.get(s.charAt(left)) - 1);
                if (visited.get(s.charAt(left)) == 0) {
                    visited.remove(s.charAt(left));
                }
                left++;
            }
        }

        return max;
    }
}
