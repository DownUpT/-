package com.dq.demo.binary;

import java.util.Arrays;

/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * <p> 2 3 4 5 6 7 1
 * If target is not found in the array, return [-1, -1].
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * <p>
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LastAndFirstArray {
    public static void main(String[] args) {
        LastAndFirstArray firstArray = new LastAndFirstArray();

        int[] ints = firstArray.searchRange(new int[]{1, 2,3}, 2);
        System.out.println(Arrays.toString(ints));
    }

    public int[] searchRange(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums.length == 1) {
            return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};
        }

        while (start < end) {
            int middle = (start + end) / 2;
            if (nums[middle] > target) {
                end = middle;
            } else if (nums[middle] < target) {
                if (start == middle) {
                    return nums[end] == target ? new int[]{end, end} : new int[]{-1, -1};
                }
                start = middle;
            } else {
                return findStartAndEnd(middle, nums, target);
            }
        }
        return new int[]{-1, -1};
    }

    private int[] findStartAndEnd(int middle, int[] nums, int target) {
        int left = middle;
        int right = middle;

        for (int i = middle; i >= 0; i--) {
            left = i;
            if (target != nums[i]) {
                break;
            }
        }

        for (int i = middle; i <= nums.length - 1; i++) {
            right = i;
            if (target != nums[i]) {
                break;
            }
        }

        if (nums[0] == target) {
            left = -1;
        }

        if (nums[nums.length - 1] == target) {
            right = nums.length;
        }

        return new int[]{left + 1, right - 1};
    }
}
