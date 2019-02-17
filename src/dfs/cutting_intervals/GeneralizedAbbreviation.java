package dfs.cutting_intervals;

import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation {
    /**
     * 2/8/2019
     * DFS on cutting interval
     *
     * @param word: the given word
     * @return: the generalized abbreviations of a word
     */
    public List<String> generateAbbreviations(String word) {
        if (word == null || word.length() == 0) {
            return new ArrayList<>();
        }

        List<String> list = new ArrayList<>();

        dfs(word, list, 0, true, "");
        dfs(word, list, 0, false, "");

        return list;
    }

    private void dfs(String word, List<String> list, int start, boolean isAbbr, String current) {
        if (start == word.length()) {
            list.add(current);
            return;
        }

        for (int end = start + 1; end <= word.length(); end++) {
            if (isAbbr) {
                dfs(word, list, end, false, current + (end - start));
            } else {
                dfs(word, list, end, true, current + word.substring(start, end));
            }
        }
    }
}
