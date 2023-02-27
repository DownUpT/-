package com.dq.demo.greed;

import java.util.Arrays;

/**
 * 贪心算法 分配问题 hard
 */
public class Candy {

    public static void main(String[] args) {
        System.out.println(getMinCandies(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(getMinCandies(new int[]{6, 2, 1, 3, 4, 5}));
        System.out.println(getMinCandies(new int[]{1, 0, 2}));
        System.out.println(getCandies(new int[]{6, 2, 1, 3, 4, 5}));
        System.out.println(getCandies(new int[]{1}));
    }

    public static int getCandies(int[] children) {
        int[] candis = new int[children.length];
        candis[0] = 1;
        for (int i = 1; i < children.length; i++) {
            if (children[i] > children[i - 1]) {
                candis[i] = candis[i - 1] + 1;
            } else {
                candis[i] = candis[i - 1];
            }
        }

        for (int i = children.length - 1; i > 0; i--) {
            if (children[i - 1] > children[i]) {
                candis[i - 1] = candis[i] + 1;
            }
        }
        return Arrays.stream(candis).sum();
    }

    public static int getMinCandies(int[] children) {
        int index = findMinChild(children);
        int rightCandy = 1;
        int leftCandy = 1;
        int totalCandy = 1;
        for (int i = index - 1; i >= 0; i--) {
            if (children[i] > children[i + 1]) {
                ++leftCandy;
            }
            totalCandy += leftCandy;
        }
        for (int i = index + 1; i < children.length; i++) {
            if (children[i] > children[i - 1]) {
                ++rightCandy;
            }
            totalCandy += rightCandy;
        }
        return totalCandy;
    }

    private static int findMinChild(int[] children) {
        int min = children[0];
        int index = 0;
        for (int i = 1; i < children.length; i++) {
            if (min > children[i]) {
                min = children[i];
                index = i;
            }
        }
        return index;
    }


}
