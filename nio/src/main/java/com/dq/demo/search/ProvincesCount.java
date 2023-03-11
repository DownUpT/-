package com.dq.demo.search;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * <p>
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * <p>
 * 返回矩阵中 省份 的数量。
 * <p>
 * <p>
 * 输入：isConnected = [
 * [1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * 输出：2
 */
public class ProvincesCount {

    public int findCircleNum(int[][] isConnected) {

        int[][] tmp = new int[isConnected.length + 2][isConnected[0].length + 2];
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[0].length; j++) {
                if (i == 0 || i == tmp.length - 1 || j == 0 || j == tmp[0].length - 1) {
                    tmp[i][j] = 0;
                } else {
                    tmp[i][j] = isConnected[i - 1][j - 1];
                }
            }
        }

        int cityNumber = 0;

        for (int i = 1; i < tmp.length - 1; i++) {
            for (int j = i; j < tmp.length - 1; j++) {
                if (tmp[i][j] != 0) {
                    cityNumber++;
                    dfs(tmp, i, j);
                    System.out.println(i + "'" + j);
                }
            }
        }
        return cityNumber;
    }

    private void dfs(int[][] isConnected, int i, int j) {
        isConnected[i][j] = 0;
        int leftx = j;
        //向右寻找
        for (; leftx < isConnected.length - 1; leftx++) {
            if (isConnected[i][leftx] == 1) {
                isConnected[i][leftx] = 0;
                dfs(isConnected, i, leftx);
            }
        }
        int downY = i;
        // 向下寻找
        for (; downY < isConnected.length - 1; downY++) {
            if (isConnected[downY][j] == 1) {
                isConnected[downY][j] = 0;
                dfs(isConnected, downY, j);
            }
        }
    }

    public static void main(String[] args) {
        ProvincesCount provincesCount = new ProvincesCount();
        int[][] isConnected = new int[][]{
                new int[]{1,0,0,1},
                new int[]{0,1,1,0},
                new int[]{0,1,1,1},
                new int[]{1,0,1,1}
        };

        System.out.println(provincesCount.findCircleNum(isConnected));

    }
}
