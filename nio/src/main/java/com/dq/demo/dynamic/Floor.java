package com.dq.demo.dynamic;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * f(n) = f(n - 1) + f(n - 2)
 */
public class Floor {

    public static void main(String[] args) {
        System.out.println(new Floor().climbStairs2(5));
        System.out.println(new Floor().climbStairs2(4));
        System.out.println(new Floor().climbStairs2(3));
        System.out.println(new Floor().climbStairs2(2));
        System.out.println(new Floor().climbStairs2(1));
        System.out.println(new Floor().climbStairs2(45));

        System.out.println(new Floor().climbStairs(45));
    }

    public int climbStairs2(int n) {
        while (n > 2) {
            return climbStairs2(n - 1) + climbStairs2(n - 2);
        }
        return n;
    }

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] ints = new int[n];
        ints[0] = 1;
        ints[1] = 2;
        int i = 2;
        while (i < n) {
            ints[i] = ints[i - 1] + ints[i -2];
            i++;
        }
        return ints[n - 1];
    }
}
