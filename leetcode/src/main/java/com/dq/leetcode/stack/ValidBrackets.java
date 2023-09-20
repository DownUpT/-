package com.dq.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * 输入：s = "()"
 * 输出：true
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 输入：s = "(]"
 * 输出：false
 */
public class ValidBrackets {
    public static void main(String[] args) {

    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char leftA = '(';
        char leftB = '[';
        char leftC = '{';
        char rightA = ')';
        char rightB = ']';
        char rightC = '}';
        for (int i = 0; i < s.length(); i++) {
            if (leftA == s.charAt(i) || leftB == s.charAt(i) || leftC == s.charAt(i)) {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (s.charAt(i) == rightA && pop != leftA) {
                    return false;
                }
                if (s.charAt(i) == rightB && pop != leftB) {
                    return false;
                }
                if (s.charAt(i) == rightC && pop != leftC) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            if (pairs.containsKey(key)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(key)) {
                    return false;
                }
            } else {
                stack.push(key);
            }
        }
        return stack.isEmpty();
    }
}
