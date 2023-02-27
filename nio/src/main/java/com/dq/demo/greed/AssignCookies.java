package com.dq.demo.greed;

/**
 * 贪心算法 分配问题 easy
 */
public class AssignCookies {

    public static int findContentChildren(int[] children, int[] cookies) {
        int child = 0;
        int cookie = 0;

        // 先排序 child 与 cookies 这里没排序
        while (child < children.length && cookie < cookies.length) {
            if (children[child] <= cookies[cookie]) {
                child++;
            }
            ++cookie;
        }
        return child;
    }

    public static void main(String[] args) {
        int contentChildren = findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3});
        System.out.println(contentChildren);
    }
}
