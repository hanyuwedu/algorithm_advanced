package dfs.continuous_enumeration;

import java.util.*;

public class LetterCombinationsofaPhoneNumber {
    /**
     * 2/6/2019
     * DFS on simple data
     *
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        Map<Character, List<Character>> code = getCode();
        Stack<Character> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        dfs(stack, list, 0, digits, code);

        return list;
    }

    private void dfs(Stack<Character> stack, List<String> list, int i, String digits, Map<Character, List<Character>> code) {
        if (i == digits.length()) {
            list.add(stackToString(stack));
            return;
        }

        for (Character c : code.get(digits.charAt(i))) {
            stack.push(c);
            dfs(stack, list, i + 1, digits, code);
            stack.pop();
        }
    }

    private String stackToString(Stack<Character> stack) {
        List<Character> list = new ArrayList<>(stack);
        StringBuilder builder = new StringBuilder();
        for (Character c : list) {
            builder.append(c);
        }

        return builder.toString();
    }


    private Map<Character,List<Character>> getCode() {
        Map<Character,List<Character>> code = new HashMap<>();
        char letter = 'a';
        for (Character digit = '2'; digit <= '9'; digit++) {
            code.put(digit, new ArrayList<>());

            for (int i = 1; i <= 4; i++) {
                if (i == 4 && digit != '7' && digit != '9') {
                    continue;
                }
                code.get(digit).add(letter);
                letter = (char) (letter + 1);
            }
        }

        return code;
    }
}
