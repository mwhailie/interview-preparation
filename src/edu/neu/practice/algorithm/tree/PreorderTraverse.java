package edu.neu.practice.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreorderTraverse {
    public static List<Integer> preOrderTraversalByRecursion(TreeNode root){
        if(root == null){
            return null;
        }
        List<Integer> res = new ArrayList<>();
        preOrderTraversalByRecursion(root, res);
        return res;
    }

    private static void preOrderTraversalByRecursion(TreeNode root, List<Integer> res){
        if(root == null){
            return;
        }
        res.add(root.value);
        preOrderTraversalByRecursion(root.left, res);
        preOrderTraversalByRecursion(root.right, res);
    }
//DFS
    public static List<Integer> preOrderTraversalByIterative(TreeNode root){
        if(root == null){
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.value);
            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(preOrderTraversalByRecursion(root));
        System.out.println(preOrderTraversalByIterative(root));
    }
}
