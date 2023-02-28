package com.dq.demo.greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组points，其中points[i] = [xstart, xend]表示水平直径在xstart和xend之间的气球。
 * 你不知道气球的确切 y 坐标。
 * <p>
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足xstart≤ x ≤ xend，则该气球会被 引爆。
 * 可以射出的弓箭的数量 没有限制 。弓箭一旦被射出之后，可以无限地前进。
 * <p>
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数。
 * <p>
 * 1 2
 * 2  3
 * 3  4
 * 4 5
 * <p>
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：气球可以用2支箭来爆破:
 * -在x = 6处射出箭，击破气球[2,8]和[1,6]。
 * -在x = 11处发射箭，击破气球[10,16]和[7,12]
 * <p>
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 * 解释：每个气球需要射出一支箭，总共需要4支箭。
 * <p>
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * 解释：气球可以用2支箭来爆破:
 * - 在x = 2处发射箭，击破气球[1,2]和[2,3]。
 * - 在x = 4处射出箭，击破气球[3,4]和[4,5]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ArrowShots {
    public static void main(String[] args) {
        List<int[]> points = new ArrayList<>();
        points.add(new int[]{2, 3});
        points.add(new int[]{1, 11});
        points.add(new int[]{4, 5});
        points.add(new int[]{7, 8});
        points.add(new int[]{10, 11});
        System.out.println(findMinArrowShots(points));

//        int[][] ps = new int[][]{new int[]{2, 3}, new int[]{1, 11}, new int[]{4, 5}, new int[]{7, 8}, new int[]{10, 11}};
        // [[3,9],[7,12],[3,8],[6,8],[9,10],[2,9],[0,9],[3,9],[0,6],[2,8]]
        int[][] ps = new int[][]{new int[]{10, 16}, new int[]{2, 8}, new int[]{1, 6}, new int[]{7, 12}};
//        int[][] ps = new int[][]{new int[]{3, 9}, new int[]{7, 12}, new int[]{3, 8}, new int[]{6, 8}, new int[]{9, 10}, new int[]{2 , 9}};
        System.out.println(findMinArrowShots(ps));
    }

    /**
     * 贪心策略: 找最小的 start   end  依次遍历
     */
    public static int findMinArrowShots(List<int[]> collect) {
        collect.sort(Comparator.comparingInt(o -> o[0]));
        int lastEnd = collect.get(0)[1];
        int n = collect.size();
        for (int i = 1; i < collect.size(); i++) {
            if (collect.get(i)[0] <= lastEnd) {
                n--;
                i++;
            } else {
                System.out.println("在x = " + collect.get(i)[1]);
            }
            lastEnd = collect.get(i)[1];
        }
        return n;
    }

    public static int findMinArrowShots(int[][] points) {

        List<int[]> collect = Arrays.stream(points).sorted(Comparator.comparingInt(o -> o[0])).collect(Collectors.toList());

        int lastEnd = collect.get(0)[1];
        int res = 1;
        int n = collect.size();
        for (int i = 1; i < collect.size(); i++) {
            if (collect.get(i)[0] > lastEnd) {
                lastEnd =collect.get(i)[1];
                ++res;
            } else {
                lastEnd = Math.min(lastEnd, collect.get(i)[1]);
                n--;
            }
        }
        System.out.println(n);
        return res;
    }
}
