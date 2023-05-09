package com.dq.demo.binary;

/**
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 0 0 0 0 0 1
 * 0 1 0 0 0 0
 * 0 0 0 0 1 0
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
 * 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 *
 * 你必须尽可能减少整个操作步骤。
 *
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 *
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 */
public class RotatedSorted {

  public static boolean search(int[] nums, int target) {
    int start = 0;
    int end = nums.length - 1;

    while (start <= end) {
      int middle = (start + end) / 2;
      if (nums[middle] == target) {
        return true;
      }
      if (nums[start] == nums[middle]) {
        start++;
      } else if (nums[middle] <= nums[end]) {
        if (target > nums[middle] && target <= nums[end]) {
          start = middle + 1;
        } else {
          end = middle - 1;
        }
      } else {
        if (target >= nums[start] && target < nums[middle]) {
          end = middle - 1;
        } else {
          start = middle + 1;
        }
      }
    }
    return false;
  }
}
