package com.dq.demo.pointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * Given two strings s and t of lengths m and n respectively,
 * return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 * <p>
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window
 * <p>
 * <p>
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SubString {
    Map<Character, AtomicInteger> ori = new HashMap<>();
    Map<Character, AtomicInteger> cur = new HashMap<>();

    public static void main(String[] args) {
        SubString subString = new SubString();
        subString.minWindow("okmijnuhb", "aacdfgha");
        System.out.println(subString.ori);
        System.out.println(subString.cur);
    }

    public String minWindow(String s, String t) {
        int start = 0;
        int end = t.length();
        if (s.length() < end) {
            return "";
        }

        for (int i = 0; i < end; i++) {
            ori.computeIfAbsent(t.charAt(i), character -> new AtomicInteger(0)).getAndIncrement();
            cur.computeIfAbsent(s.charAt(i), character -> new AtomicInteger(0)).getAndIncrement();
        }

        while (end < s.length()) {
            if (match(ori, cur)) {

            }
        }


        return "";
    }

    private boolean match(Map<Character, AtomicInteger> ori, Map<Character, AtomicInteger> cur) {
        for (Map.Entry<Character, AtomicInteger> entry : ori.entrySet()) {
            if (!cur.containsKey(entry.getKey())) {
                return false;
            }

            if (entry.getValue().get() != cur.get(entry.getKey()).get()) {
                return false;
            }
        }
        return true;
    }
}
