package edu.neu.companies.microsoft;

import edu.neu.algorithm.tree.TreeNode;

import java.util.*;

public class BinaryTreeRightSideView {
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rightSideView(root, res);
        return res;
    }
    public static void rightSideView(TreeNode root, List<Integer> res) {
        if(root == null){
            return;
        }
        System.out.print(root.value);
        res.add(root.value);
        if(root.right != null){
            rightSideView(root.right, res);
        }else {
            rightSideView(root.left, res);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println(rightSideView(root));
    }
}
