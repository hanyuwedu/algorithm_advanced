package gameday;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    /**
     * 2/8/2016
     * DFS on cutting intervals
     *
     * @param num: a string contains only digits 0-9
     * @param target: An integer
     * @return: return all possibilities
     */
    public List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0) {
            return new ArrayList<>();
        }

        List<String> list = new ArrayList<>();
        dfs(0, 0, 0, "", num, Long.valueOf(target), list);

        return list;
    }

    private void dfs(int start, long current, long lastOperator, String expression, String num, long target, List<String> list) {
        if (start == num.length()) {
            if (current == target) {
                list.add(expression);
            }
            return;
        }

        for (int end = start + 1; end <= num.length(); end++) {
            if (end > start + 1 && num.charAt(start) == '0') {
                break;
            }

            long next = Long.valueOf(num.substring(start, end));

            if (start == 0) {
                dfs(end, next, next, "" + next, num, target, list);
                continue;
            }

            dfs(end, current + next, next, expression + "+" + next, num, target, list);
            dfs(end, current - next, -next, expression + "-" + next, num, target, list);
            dfs(end, current - lastOperator + lastOperator * next, lastOperator * next, expression + "*" + next, num, target, list);
        }
    }
}
