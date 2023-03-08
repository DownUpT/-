package com.dq.demo.binary;

/**
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
 * The returned integer should be non-negative as well.
 * <p>
 * <p>
 * Input: x = 4
 * Output: 2
 * Explanation: The square root of 4 is 2, so we return 2.
 * <p>
 * <p>
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
 */
public class Sqrt {

    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        System.out.println(sqrt.mySqrt(16));
    }

    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        long end = x / 2;
        long start = 0;
        while (start != end) {
            long middle = (end + start) / 2;
            if (middle * middle > x) {
                end = middle;
            } else if (middle * middle < x) {
                if (start == middle) {
                    return end * end > x ? (int) middle : (int) end;
                }
                start = middle;
            } else {
                return (int) middle;
            }
        }
        return (int) start;
    }
}
