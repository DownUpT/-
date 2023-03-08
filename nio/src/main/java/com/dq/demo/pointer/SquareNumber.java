package com.dq.demo.pointer;

/**
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 * <p>
 * Input: c = 5
 * Output: true
 * Explanation: 1 * 1 + 2 * 2 = 5
 * <p>
 * Input: c = 3
 * Output: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-of-square-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SquareNumber {
    public static void main(String[] args) {
        SquareNumber squareNumber = new SquareNumber();
        System.out.println(1 + ": " + squareNumber.judgeSquareSum2(1));
        System.out.println(2 + ": " + squareNumber.judgeSquareSum2(2));
        System.out.println(3 + ": " + squareNumber.judgeSquareSum2(3));
        System.out.println(4 + ": " + squareNumber.judgeSquareSum2(4));
        System.out.println(5 + ": " + squareNumber.judgeSquareSum2(5));
        System.out.println(6 + ": " + squareNumber.judgeSquareSum2(6));
        System.out.println(7 + ": " + squareNumber.judgeSquareSum2(7));
        System.out.println(8 + ": " + squareNumber.judgeSquareSum2(8));
        System.out.println(9 + ": " + squareNumber.judgeSquareSum2(9));
        System.out.println(2147482647 + ": " + squareNumber.judgeSquareSum2(2147482647));
    }

    public boolean judgeSquareSum(int c) {
        if (c == 1 || c == 0) {
            return true;
        }
        long left = 0;
        long right;
        int numberLen = String.valueOf(c).length();
        if (numberLen >= 5) {
            StringBuilder sb = new StringBuilder("1");
            for (int i = 0; i < (numberLen - 1) / 2; i++) {
                sb.append("0");
            }
            int i = Integer.parseInt(sb.toString());
            right = c / i;
        } else {
            right = c / 2;
        }

        while (left <= right) {
            if (left * left + right * right == c) {
                return true;
            } else if (left * left + right * right < c) {
                left++;
            } else if (left * left + right * right > c) {
                right--;
            }
        }

        return false;
    }


    public boolean judgeSquareSum2(int c) {

        long left = 0;
        long right = (long) Math.sqrt(c);

        while (left <= right) {
            if (left * left + right * right == c) {
                return true;
            } else if (left * left + right * right < c) {
                left++;
            } else if (left * left + right * right > c) {
                right--;
            }
        }

        return false;
    }
}
