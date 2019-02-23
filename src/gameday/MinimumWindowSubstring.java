package gameday;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    /**
     * 2/22/2019
     * GameDay
     * https://www.lintcode.com/problem/minimum-window-substring/description
     *
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
    public String minWindow(String source , String target) {
        if (source == null || source.length() == 0) {
            return "";
        }

        Map<Character, Integer> targetFreq = new HashMap<>(), sourceFreq = new HashMap<>();

        int distance = 0;
        for (Character c : target.toCharArray()) {
            targetFreq.put(c, targetFreq.getOrDefault(c, 0) + 1);
            distance++;
        }

        int left = 0, right = 0, min = Integer.MAX_VALUE;
        String result = "";

        while (true) {
            if (distance == 0) {
                if (right - left < min) {
                    result = source.substring(left, right);
                    min = right - left;
                }

                char c = source.charAt(left);

                sourceFreq.put(c, sourceFreq.get(c) - 1);
                if (targetFreq.containsKey(c) && targetFreq.get(c) > sourceFreq.get(c)) {
                    distance++;
                }
                left++;
            } else {
                if (right == source.length()) {
                    break;
                }
                char c = source.charAt(right);

                sourceFreq.put(c, sourceFreq.getOrDefault(c, 0) + 1);
                if (targetFreq.containsKey(c) && targetFreq.get(c) >= sourceFreq.get(c)) {
                    distance--;
                }
                right++;
            }
        }

        return result;
    }
}
