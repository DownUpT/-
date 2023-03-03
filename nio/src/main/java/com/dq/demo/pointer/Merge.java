package com.dq.demo.pointer;

import java.util.Arrays;

/**
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
 * representing the number of elements in nums1 and nums2 respectively.
 * <p>
 * <p>
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * <p>
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
 * and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 * <p>
 * <p>1 2   3
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Merge {
    public static void main(String[] args) {
//        int[] num1 = {1, 2, 3, 0, 0, 0};
//        int[] num2 = {2, 5, 6};
        int[] num1 = {0};
        int[] num2 = {1};
        int m = 0;
        int n = 1;
        Merge merge = new Merge();
        merge.merge(num1, m, num2, n);
        System.out.println(Arrays.toString(num1));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[n + m];
        while (m > 0 || n > 0) {
            if (m == 0) {
                result[n + m - 1] = nums2[n - 1];
                n--;
            } else if (n == 0) {
                result[n + m - 1] = nums1[m - 1];
                m--;
            } else if (nums1[m - 1] >= nums2[n - 1]) {
                result[n + m - 1] = nums1[m - 1];
                m--;
            } else {
                result[n + m - 1] = nums2[n - 1];
                n--;
            }
        }
        for (int i = 0; i != result.length; ++i) {
            nums1[i] = result[i];
        }
    }
}
