package com.dq.demo.greed;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 贪心算法 区间问题 medium
 */
public class NonOverlappingIntervals {
    /*
    1 2   1  3   2   4   3  5
    1 2  1 3  2 4  3 5
    1 1 2 3 5
    2 3 5 4 6
    按找0索引排序数组
    遍历数组列表
    剔除值
     */

    public static void main(String[] args) {
        // 1 2  | 1  3  | 1  4  | 1  5  |  2 3 |  3 5
        // 1 2  2 3   3 5
        List<int[]> laps = new ArrayList<>();
        laps.add(new int[]{1, 2});
        laps.add(new int[]{2, 3});
        laps.add(new int[]{2, 4});
        laps.add(new int[]{1, 3});
        System.out.println(removeOverlapping(laps));
    }

    public static int removeOverlapping(List<int[]> laps) {
        laps.sort(Comparator.comparingInt(o -> o[1]));
        laps.forEach(lap -> System.out.println(lap[0] + ":" + lap[1]));
        int[] tem = laps.get(0);
        int remove = 0;
        for (int i = 1; i < laps.size(); i++) {
            int[] lap = laps.get(i);
            if (lap[0] < tem[1]) {
                remove++;
                System.out.println("remove: [" + lap[0] + ", " + lap[1] + "]");
                continue;
            }
            tem = lap;
        }
        return remove;
    }
}
