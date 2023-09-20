package com.dq.leetcode.array;

import java.util.Arrays;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * <p>
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3]
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] ints = {};
        int i = removeElement(ints, 2);
        System.out.println(i);
        System.out.println(Arrays.toString(ints));
    }

    public static int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left++] = nums[right];
            }
        }
        return left;
    }

    public static int removeElement2(int[] nums, int val) {
        int right = nums.length;
        int left = 0;
        while (left < right) {
            if (nums[left] == val) {
               nums[left] = nums[right - 1];
               right--;
            } else {
                left++;
            }
        }
        return left;
    }
}