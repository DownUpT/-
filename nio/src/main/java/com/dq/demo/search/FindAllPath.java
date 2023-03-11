package com.dq.demo.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * [      1     ]
 * [  2      3  ]
 * [ 5          ]
 * <p>
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 * <p>
 * 输入：root = [1]
 * 输出：["1"]
 */
public class FindAllPath {

    public static void main(String[] args) {
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2, null, treeNode5);
        TreeNode treeNode1 = new TreeNode(1, treeNode2, treeNode3);

        FindAllPath allPath = new FindAllPath();
        List<String> strings = allPath.binaryTreePaths(treeNode1);
        System.out.println(strings);

    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        bfs(result, root, "");
        return result;
    }

    private void bfs(List<String> result, TreeNode node, String path) {
        if (node.left != null) {
            bfs(result, node.left, path + node.val + "->");
        }

        if (node.right != null) {
            bfs(result, node.right, path + node.val + "->");
        }
        if (node.right == null && node.left == null) {
            result.add(path + node.val);
        }
    }
}

/**
 * Definition for a binary tree node.
 */
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
        this.val = val;
        this.left = left;
        this.right = right;
    }
}