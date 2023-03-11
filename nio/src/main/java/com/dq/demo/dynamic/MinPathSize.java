package com.dq.demo.dynamic;

/**
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 输入：grid = [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 */
public class MinPathSize {
    public static void main(String[] args) {
        MinPathSize pathSize = new MinPathSize();
        System.out.println(pathSize.minPathSum(new int[][]{new int[]{1, 3, 1}, new int[]{1, 5, 1}, new int[]{4, 2, 1}}));
        System.out.println(pathSize.minPathSum(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}}));
    }

    public int minPathSum(int[][] grid) {
        int[][] tmp = new int[grid.length][grid[0].length];
        tmp[0][0] = grid[0][0];
        if (grid.length == 1 && grid[0].length == 1) {
            return grid[0][0];
        }
        for (int j = 1; j < tmp[0].length; j++) {
            tmp[0][j] = grid[0][j] + tmp[0][j - 1];
        }
        for (int j = 1; j < tmp.length; j++) {
            tmp[j][0] = grid[j][0] + tmp[j - 1][0];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                tmp[i][j] = Math.min(tmp[i - 1][j], tmp[i][j - 1]) + grid[i][j];
            }
        }

        return tmp[grid.length - 1][grid[0].length - 1];
    }
}
