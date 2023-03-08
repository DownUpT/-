package com.dq.demo.pointer;

/**
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 * <p>
 * <p>
 * Input: s = "aba"
 * Output: true
 * <p>
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * <p>
 * Input: s = "abc"
 * Output: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-palindrome-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * abcdefa
 * bcdef
 * c != f || b != e
 */
public class ValidPalindrome {

    public static void main(String[] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
//        System.out.println(validPalindrome.validPalindrome("a"));
//        System.out.println(validPalindrome.validPalindrome("ab"));
//        System.out.println(validPalindrome.validPalindrome("abca"));
//        System.out.println(validPalindrome.validPalindrome("abcfdba"));
        System.out.println(validPalindrome.validPalindrome("acxcybycxcxa"));
    }

    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        int count = 0;
        while (left <= right) {
            System.out.println(s.substring(left, right + 1));
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                if (s.charAt(left + 1) == s.charAt(right) && s.charAt(left) == s.charAt(right - 1)) {
                    if (left + 2 > right - 1) {
                        return true;
                    }
                    if ((s.charAt(left + 2) == s.charAt(right - 1))) {
                        left++;
                    } else if (s.charAt(right - 2) == s.charAt(left + 1)) {
                        right--;
                    } else {
                        return false;
                    }
                } else if (s.charAt(left + 1) == s.charAt(right) && !(s.charAt(left) == s.charAt(right - 1))) {
                    left++;
                } else if (s.charAt(left) == s.charAt(right - 1) && !(s.charAt(left + 1) == s.charAt(right))) {
                    right--;
                } else {
                    return false;
                }
                count++;
            }
        }
        return count <= 1;
    }
}
