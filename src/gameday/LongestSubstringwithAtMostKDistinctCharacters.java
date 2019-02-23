package gameday;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringwithAtMostKDistinctCharacters {
    /**
     * 2/22/2019
     * GameDay
     * https://www.lintcode.com/problem/longest-substring-with-at-most-k-distinct-characters/description
     *
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> freq = new HashMap<>();
        int max = 0;

        int left = 0, right = 0;

        while (true) {
            if (freq.size() <= k) {
                max = Math.max(max, right - left);
                if (right == s.length()) {
                    break;
                }
                char next = s.charAt(right);

                freq.put(next, freq.getOrDefault(next, 0) + 1);
                right++;
            } else {
                char next = s.charAt(left);
                freq.put(next, freq.get(next) - 1);
                if (freq.get(next) == 0) {
                    freq.remove(next);
                }
                left++;
            }
        }

        return max;
    }
}
