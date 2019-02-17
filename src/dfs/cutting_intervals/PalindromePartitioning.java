package dfs.cutting_intervals;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PalindromePartitioning {
    /**
     * 2/9/2019
     * DFS on start
     *
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }

        Stack<String> stack = new Stack<>();
        List<List<String>> list = new ArrayList<>();
        boolean[][] isPalindrome = palindrome(s);

        // for (int i = 1; i <= s.length(); i++) {
        //     dfs_on_start_end(0, i, stack, list, isPalindrome, s);
        // }

        dfs_on_start(0, stack, list, isPalindrome, s);


        return list;
    }

    private void dfs_on_start(int left, Stack<String> stack, List<List<String>> list, final boolean[][] isPalindrome, final String s) {
        if (left == s.length()) {
            list.add(new ArrayList<>(stack));
            return;
        }

        for (int right = left + 1; right <= s.length(); right++) {
            if (isPalindrome[left][right]) {
                stack.push(s.substring(left, right));
                dfs_on_start(right, stack, list, isPalindrome, s);
                stack.pop();
            }
        }
    }


    private void dfs_on_start_end(int start, int end, Stack<String> stack, List<List<String>> list, final boolean[][] isPalindrome, final String s) {
        if (!isPalindrome[start][end]) {
            return;
        }

        stack.push(s.substring(start, end));

        if (end == s.length()) {
            list.add(new ArrayList<>(stack));
            stack.pop();
            return;
        }


        for (int j = end + 1; j <= s.length(); j++) {
            dfs_on_start_end(end, j, stack, list, isPalindrome, s);
        }

        stack.pop();
    }

    private boolean[][] palindrome(final String s) {
        int len = s.length();

        boolean[][] isPalindrome = new boolean[len][len + 1];

        for (int j = 0; j <= len - 1; j++) {
            isPalindrome[j][j] = true;
            isPalindrome[j][j + 1] = true;
        }

        for (int i = 2; i <= len; i++) {
            for (int j = 0; j <= len - 1; j++) {
                if (i + j > len) {
                    break;
                }

                isPalindrome[j][j + i] = isPalindrome[j + 1][i + j - 1] && s.charAt(j) == s.charAt(j + i - 1);
            }
        }

        return isPalindrome;
    }
}
