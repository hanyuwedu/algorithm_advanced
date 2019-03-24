package pointers.window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * 1/23/2019
     * Chasing pointers
     *
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0, right = 0;
        Map<Character, Integer> visited = new HashMap<>();
        int max = 0;

        while (true) {
            if (visited.size() == right - left) {
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



    /**
     * 1/22/2019
     * Chasing pointers
     *
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring_next_element(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0, right = 0;
        Set<Character> visited = new HashSet<>();
        int max = 0;

        while (right < s.length()) {
            if (visited.contains(s.charAt(right))) {
                visited.remove(s.charAt(left));
                left++;
            } else {
                visited.add(s.charAt(right));
                right++;
                max = Math.max(max, right - left);
            }
        }

        return max;
    }

    /**
     * 1/22/2019
     * Chasing pointers in coordinate
     *
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring_coordinate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0, right = 0;
        Set<Character> visited = new HashSet<>();
        int max = 1; visited.add(s.charAt(0));

        while (right < s.length() - 1) {
            if (visited.contains(s.charAt(right + 1))) {
                visited.remove(s.charAt(left));
                left++;
            } else {
                visited.add(s.charAt(right + 1));
                right++;
                max = Math.max(max, right - left + 1);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("aaaa"));
    }
}
