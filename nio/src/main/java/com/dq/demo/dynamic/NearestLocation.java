package com.dq.demo.dynamic;

/**
 * 给定一个由 0 和 1 组成的矩阵 mat，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 * <p>
 * 两个相邻元素间的距离为 1 。
 * <p>
 * <p>
 * 输入：mat = [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]]
 * 输出：[
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]]
 * <p>
 * 输入：mat = [
 * [0,0,0],
 * [0,1,0],
 * [1,1,1]]
 * 输出：[
 * [0,0,0],
 * [0,1,0],
 * [1,2,1]]。
 */
public class NearestLocation {
    public static void main(String[] args) {
        NearestLocation nearestLocation = new NearestLocation();
        int[][] mat = {new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}};
        nearestLocation.updateMatrix(mat);
    }

    public int[][] updateMatrix(int[][] mat) {
        int[][] tmp = new int[mat.length + 2][mat[0].length + 2];
        for (int i = 1; i < tmp.length - 1; i++) {
            if (tmp[0].length - 1 - 1 >= 0) {
                System.arraycopy(mat[i - 1], 0, tmp[i], 1, tmp[0].length - 1 - 1);
            }
        }
        for (int[] ints : tmp) {
            for (int anInt : ints) {
                System.out.print(anInt + ", ");
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 1; i < tmp.length - 1; i++) {
            for (int j = 1; j < tmp[0].length - 1; j++) {
                if (tmp[i][j] == 1) {
                    if (tmp[i][j - 1] == 0 || tmp[i - 1][j] == 0) {
                        mat[i - 1][j - 1] = 1;
                    } else {
                        mat[i - 1][j - 1] = Math.max(mat[i - 1][j - 2], mat[i - 2][j - 1]) + 1;
                    }
                }
            }
        }

        for (int i = tmp.length - 2; i > 0; i--) {
            for (int j = tmp[0].length - 2; j > 0; j--) {
                if (tmp[i][j] == 1) {
                    if (tmp[i][j + 1] == 0 || tmp[i + 1][j] == 0) {
                        mat[i - 1][j - 1] = Math.max(mat[i - 1][j - 1], 1);
                    } else {
                        int size = Math.max(mat[i - 1][j - 2], mat[i - 2][j - 1]) + 1;
                        mat[i - 1][j - 1] = Math.max(mat[i - 1][j - 2], mat[i - 2][j - 1]) + 1;
                    }
                }
            }
        }

        for (int[] ints : mat) {
            for (int anInt : ints) {
                System.out.print(anInt + ", ");
            }
            System.out.println();
        }
        return tmp;
    }
}
