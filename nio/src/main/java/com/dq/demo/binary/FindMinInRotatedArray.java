package com.dq.demo.binary;

/**
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *
 * 你必须尽可能减少整个过程的操作步骤。
 * 4 4 4 4 4 1
 * 1 2 3 4 4 4
 * 4 12344
 * 441234
 * 444123
 *
 */
public class FindMinInRotatedArray {

  public static void main(String[] args) {

  }


  public static int findMin(int[] nums) {
    // 每次旋转都拿尾部元素放到头部。整体的还是两个升序部分组成
    // 求最小值，判断条件为找到两个升序数组的组装点，即右边小于左边，或者index = 0；
    // 如果 start 《 end ，start为最小
    // 如果 start = middle || 不能判断最小在哪边
    // 不能判断就要分
    // 如果 start < middle 并且 最小值在右边部分
    // 如果 middle < end 最小值在左边部分

    if (nums.length == 1) {
      return nums[0];
    }
    int end = nums.length - 1;
    int start = 0;
    int middle = (start + end) / 2;
    while (start < end) {
      if (nums[start] < nums[end]) {
        return nums[start];
      }

      if (nums[start] < nums[middle]) {
        start = middle + 1;
        middle = (start + end) / 2;
      } else if (nums[start] > nums[middle]) {
        end = middle;
        start++;
        middle = (start + end) / 2;
      } else {
        start = start + 1;
        middle = (start + end) / 2;
      }
    }
    return nums[middle];
  }
}
