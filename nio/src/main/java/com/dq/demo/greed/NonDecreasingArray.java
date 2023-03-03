package com.dq.demo.greed;

/**
 * 给你一个长度为n的整数数组nums，请你判断在 最多 改变1 个元素的情况下，该数组能否变成一个非递减数列。
 * <p>
 * 我们是这样定义一个非递减数列的：对于数组中任意的i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * <p>
 * <p>
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个 4 变成 1 来使得它成为一个非递减数列。
 * <p>
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 */
public class NonDecreasingArray {

    public static void main(String[] args) {
        NonDecreasingArray array = new NonDecreasingArray();
        System.out.println(array.checkPossibility(new int[]{3, 4, 2, 3}));
        System.out.println(array.checkPossibility(new int[]{1, 2, 5, 1, 3, 4}));
        System.out.println(array.checkPossibility(new int[]{4, 2, 3}));
        System.out.println(array.checkPossibility(new int[]{5, 7, 1, 8}));
        System.out.println(array.checkPossibility(new int[]{1,3,5,2,4}));
    }

    public boolean checkPossibility(int[] nums) {
        int count = 0;
        int minIndex = 0;
        int maxIndex = 0;
        int min = nums[0];
        int max = nums[0];
        for (int i = 0; i <= nums.length - 1; i++) {
            if (i < nums.length - 1 && nums[i] > nums[i + 1]) {
                count++;
            }

            if (min >= nums[i]) {
                min = nums[i];
                minIndex = i;
            }

            if (max <= nums[i]) {
                max = nums[i];
                maxIndex = i;
            }
        }

        int biggerCount = 0;
        for (int i = minIndex; i > 0; i--) {
            if (nums[i - 1] > nums[minIndex]) {
                biggerCount++;
            }
        }

        return count <= 1 && (biggerCount < 2 && nums.length - 1 - maxIndex < 2);
    }
}
