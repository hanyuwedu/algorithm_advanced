package dynamic_programming.memorized_search;

import java.util.HashMap;
import java.util.Map;

public class ScrambleString {
    /**
     * 12/27
     * Backpack DP
     *
     * @param s1: A string
     * @param s2: Another string
     * @return: whether s2 is a scrambled string of s1
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        Map<String, Boolean> dp = new HashMap<>();
        return search(s1, s2, dp);
    }

    private boolean search(String s1, String s2, Map<String, Boolean> dp) {
        if (s1.equals(s2)) {
            return true;
        }

        String key = s1 + ", " + s2;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        boolean value = false;
        int n = s1.length();
        for (int i = 1; i <= s1.length() - 1; i++) {
            if (search(s1.substring(0, i), s2.substring(0, i), dp)
                    && search(s1.substring(i, n), s2.substring(i, n), dp)) {
                value = true;
                break;
            }

            if (search(s1.substring(0, i), s2.substring(n - i, n), dp)
                    && search(s1.substring(i, n), s2.substring(0, n - i), dp)) {
                value = true;
                break;
            }
        }

        dp.put(key, value);
        return value;
    }
}
