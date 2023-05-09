package com.dq.demo.binary;

/**
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * <p>
 * 请你找出并返回只出现一次的那个数。
 * <p>
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * <p>
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * <p>
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 */
public class OrderSingleElement {

  public static void main(String[] args) {
    int[] nums = new int[] {3, 3, 7, 7, 10, 11, 11};
    System.out.println(singleNonDuplicate(nums));


    System.out.println(4 ^ 1); // 100 => 101
    System.out.println(5 ^ 1); // 101 => 100
    System.out.println(6 ^ 1); // 110 => 111
    System.out.println(7 ^ 1); // 111 => 110

  }

  /**
   * 思路
   * 时间复杂度来看，要用二分查找
   * <p>
   * 找到中点怎么做？
   * 如果那边没有那边就应该是偶数个
   *
   * @param nums
   * @return
   */
  public static int singleNonDuplicate(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    }
    int start = 0; int end = nums.length - 1; int mid = (start + end) / 2;
    while (start < end) {
      if (start - end == 2) {
        return nums[mid] == nums[start] ? nums[end] : nums[start];
      }
      // 找是在左边还是右边
      if (nums[mid] == nums[mid - 1]) {
        if ((mid - start + 1) % 2 == 0) {
          // 在右边
          start = mid + 1;
        } else {
          // 在左边
          end = mid - 2;
        }
        mid = (start + end) / 2;
      } else if (nums[mid] == nums[mid + 1]) {
        if ((end - mid + 1) % 2 == 0) {
          // 在左边
          end = mid - 1;
        } else {
          // 在左边
          start = mid + 2;
        }
        mid = (start + end) / 2;
      } else {
        return nums[mid];
      }
    } return nums[start];
  }

  public static int singleNonDuplicate2(int[] nums) {
    int low = 0, high = nums.length - 1;
    while (low < high) {
      int mid = (high - low) / 2 + low;
      if (nums[mid] == nums[mid ^ 1]) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    return nums[low];
  }
}
