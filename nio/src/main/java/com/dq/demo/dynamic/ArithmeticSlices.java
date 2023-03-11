package com.dq.demo.dynamic;

public class ArithmeticSlices {

    public static void main(String[] args) {
        ArithmeticSlices arithmeticSlices = new ArithmeticSlices();
        int i = arithmeticSlices.numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 6});
        System.out.println(i);
    }

    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        int[] tmp = new int[nums.length];
        tmp[0] = 0;
        tmp[1] = 0;

        for (int i = 2; i < nums.length; i++) {
            tmp[i] = slicesNum(nums, i);
        }
        int sum = 0;
        for (int j : tmp) {
            sum += j;
        }
        return sum;
    }

    private int slicesNum(int[] nums, int i) {
        int count = 0;
        boolean flag = true;
        while (i >= 2 && flag) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                count++;
                i--;
            } else {
                flag = false;
            }
        }
        return count;
    }
}
