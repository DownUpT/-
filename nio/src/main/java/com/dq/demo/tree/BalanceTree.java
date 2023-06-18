package com.dq.demo.tree;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树
 * 本题中，一棵高度平衡二叉树定义为：
 * |     一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 */
public class BalanceTree {
  public static void main(String[] args) {

  }


  public boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }

    return Math.abs(depth(root.right) - depth(root.left)) <= 1;
  }

  /**
   * 错误的办法，没有考虑到子树也可能是不平衡的
   * @param node
   * @return
   */
  public int depth(TreeNode node) {
    if (node == null) {
      return 0;
    }

    return 1 + Math.max(depth(node.left), depth(node.right));
  }
}
