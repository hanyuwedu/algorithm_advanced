package gameday;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    /**
     * 2/22/2019
     * GameDay
     * https://www.lintcode.com/problem/expression-add-operators/description
     *
     * @param num: a string contains only digits 0-9
     * @param target: An integer
     * @return: return all possibilities
     */
    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        dfs(num, target, 0, 0, 0, result, "");

        return result;
    }

    private void dfs(String num, long target, int start, long sum, long last, List<String> result, String current) {
        if (start == num.length()) {
            if (sum == target) {
                result.add(current);
            }
            return;
        }

        for (int i = start + 1; i <= num.length(); i++) {
            if (num.charAt(start) == '0' && i > start + 1) {
                break;
            }

            long next = Long.valueOf(num.substring(start, i));

            if (start == 0) {
                dfs(num, target, i, next, next, result, "" + next);
            } else {
                dfs(num, target, i, sum + next, next, result, current + "+" + next);
                dfs(num, target, i, sum - next, -next, result, current + "-" + next);
                dfs(num, target, i, sum - last + last * next, last * next, result, current + "*" + next);
            }
        }
    }
}
