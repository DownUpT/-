package com.dq.demo.search;

/**
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * <p>
 * 岛屿是由一些相邻的1(代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * <p>
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 * <p>
 * <p>
 * 输入：grid = [
 * [0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出：6
 * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 * <p>
 * 输入：grid = [[0,0,0,0,0,0,0,0]]
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/max-area-of-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxArea {
    int res;

    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        int[][] grid = new int[][]{
                new int[]{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                new int[]{0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                new int[]{0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                new int[]{0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        System.out.println(maxArea.maxAreaOfIsland(grid));
        int[][] grid2 = new int[][]{
                new int[]{1, 1, 1}, new int[]{1, 0, 1}};
        System.out.println(maxArea.maxAreaOfIsland(grid2));
    }

    public int maxAreaOfIsland(int[][] grid) {
        int[][] tmp = new int[grid.length + 2][grid[0].length + 2];
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[0].length; j++) {
                if (i == 0 || i == tmp.length - 1 || j == 0 || j == tmp[0].length - 1) {
                    tmp[i][j] = 0;
                } else {
                    tmp[i][j] = grid[i - 1][j - 1];
                }
            }
        }
        int max = 0;
        for (int i = 1; i < tmp.length - 1; i++) {
            for (int j = 1; j < tmp[0].length - 1; j++) {
                if (tmp[i][j] != 0) {
                    res = 0;
                    dfs(tmp, i, j);
                    max = Math.max(res, max);
                }
            }
        }
        return max;
    }

    private void dfs(int[][] tmp, int i, int j) {
        tmp[i][j] = 0;
        if (tmp[i][j - 1] != 0) {
            dfs(tmp, i, j - 1);
        }

        if (tmp[i - 1][j] != 0) {
            dfs(tmp, i - 1, j);
        }

        if (tmp[i + 1][j] != 0) {
            dfs(tmp, i + 1, j);
        }

        if (tmp[i][j + 1] != 0) {
            dfs(tmp, i, j + 1);
        }
        res++;
    }
}
