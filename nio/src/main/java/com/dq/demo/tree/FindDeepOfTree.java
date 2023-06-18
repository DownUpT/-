package com.dq.demo.tree;

/**
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 *
 *   3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 *    返回它的最大深度 3 。
 */
public class FindDeepOfTree {

  public static void main(String[] args) {
      TreeNode treeNode = new TreeNode(3);
      System.out.println(maxDepth(treeNode));
  }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}


// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val; this.left = left; this.right = right;
  }
}
