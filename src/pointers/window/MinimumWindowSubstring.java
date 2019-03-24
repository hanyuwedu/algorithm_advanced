package pointers.window;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    /**
     * 1/22/2019
     * Chasing window
     *
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
    public String minWindow(String source , String target) {
        if (source == null || target == null || source.length() == 0 || target.length() == 0) {
            return "";
        }

        Map<Character, Integer> targetCount = new HashMap<>(), sourceCount = new HashMap<>();
        int gap = target.length();

        for (Character c : target.toCharArray()) {
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
            sourceCount.put(c, 0);
        }

        int left = 0, right = 0;
        int min = Integer.MAX_VALUE;
        String str = "";

        while (true) {
            if (gap == 0) {
                if (min > right - left) {
                    str = source.substring(left, right);
                    min = right - left;
                }

                if (targetCount.containsKey(source.charAt(left))) {
                    sourceCount.put(source.charAt(left), sourceCount.get(source.charAt(left)) - 1);
                    if (sourceCount.get(source.charAt(left)) < targetCount.get(source.charAt(left))) {
                        gap++;
                    }
                }

                left++;
            } else {
                if (right == source.length()) {
                    break;
                }

                if (targetCount.containsKey(source.charAt(right))) {
                    sourceCount.put(source.charAt(right), sourceCount.get(source.charAt(right)) + 1);
                    if (sourceCount.get(source.charAt(right)) <= targetCount.get(source.charAt(right))) {
                        gap--;
                    }
                }

                right++;
            }
        }

        return str;
    }
}
