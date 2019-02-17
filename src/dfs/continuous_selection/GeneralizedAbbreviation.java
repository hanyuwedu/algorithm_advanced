package dfs.continuous_selection;

import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation {
    /**
     * 2/8/2019
     * DFS on continuous selection
     *
     * @param word: the given word
     * @return: the generalized abbreviations of a word
     */
    public List<String> generateAbbreviations(String word) {
        if (word == null || word.length() == 0) {
            return new ArrayList<>();
        }


        List<String> list = new ArrayList<>();
        boolean[] abbrs = new boolean[word.length()];

        dfs(abbrs, word, list, 0);
        return list;
    }

    private void dfs(boolean[] abbrs, String word, List<String> list, int i) {
        if (i == word.length()) {
            list.add(getAbbr(word, abbrs));
            return;
        }

        dfs(abbrs, word, list, i + 1);
        abbrs[i] = true;
        dfs(abbrs, word, list, i + 1);
        abbrs[i] = false;
    }

    private String getAbbr(String word, boolean[] abbr) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < abbr.length) {
            if (!abbr[i]) {
                builder.append(word.charAt(i));
                i++;
            } else {
                int j = i;
                while (j < abbr.length && abbr[j]) {
                    j++;
                }

                builder.append(j - i);
                i = j;
            }
        }

        return builder.toString();
    }
}
