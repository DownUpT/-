package com.dq.demo.greed;

import java.util.ArrayList;
import java.util.List;

/**
 * [1,0,0,0,0,0,1]
 * [0,1,0,1,0,0,1]
 * 1 0 1 0 1
 * 1 0 0 0 0 0 0 1
 * 0 0 0 0 0 0 0
 * 遇到0 0 0组合就可以种花，种花后成0 1 0
 * 0 0 0组合在数组边界存在特殊情况，这里我们用防御式编程思想，在flowerbed数组两端各添加一个0；
 * 每种一朵花，就让n--，最后判断n是否小于等于0的真假就可以了
 */
public class FlowerBed {

    public static void main(String[] args) {
        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 1}, 1));
        System.out.println(canPlaceFlowers(new int[]{0, 0, 0, 1}, 1));
        System.out.println(canPlaceFlowers(new int[]{0, 0, 0, 0}, 2));
        System.out.println(canPlaceFlowers(new int[]{0, 0, 0, 0}, 1));
        System.out.println(canPlaceFlowers(new int[]{0, 0, 0, 0}, 3));
    }


    public static boolean canPlaceFlowers(int[] flowerBed, int count) {
        //
        int[] newBed = new int[flowerBed.length + 2];
        System.arraycopy(flowerBed, 0, newBed, 1, flowerBed.length);
        for (int i = 1; i < newBed.length - 1; i++) {
            if (newBed[i] == 0 && newBed[i - 1] == 0 && newBed[i + 1] == 0) {
                count--;
                newBed[i] = 1;
            }
        }

        return count <= 0;
    }
}
