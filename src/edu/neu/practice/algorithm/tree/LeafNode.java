package edu.neu.practice.algorithm.tree;

import java.util.Stack;

public class LeafNode {
    public static int numberOfLeafNode(TreeNode root){
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }
        return numberOfLeafNode(root.left) + numberOfLeafNode(root.right);
    }
    public static int numberOfLeafNodeIterative(TreeNode root){
        if(root == null){
            return 0;
        }
        int num = 0;
        Stack<TreeNode> s = new Stack<>();
        TreeNode node = root;
        while(node != null || !s.isEmpty()){
            while(node != null){
                s.push(node);
                node = node.left;
            }
            node = s.pop();
            if(node.left == null && node.right == null){
                num ++;
            }
            node = node.right;
        }
        return num;
    }
    public static int numberOfLeafNodePreOrderIterative(TreeNode root){
        if(root == null){
            return 0;
        }
        int num = 0;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while(!s.isEmpty()){
            TreeNode node = s.pop();
//            System.out.println(node.value);
            if(node.left == null && node.right == null){
                num ++;
                continue;
            }
            if(node.right != null){
                s.push(node.right);
            }
            if(node.left != null){
                s.push(node.left);
            }
        }
        return num;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(numberOfLeafNode(root));
        System.out.println(numberOfLeafNodeIterative(root));
        System.out.println(numberOfLeafNodePreOrderIterative(root));
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.left.left.left.left = new TreeNode(5);
        System.out.println(numberOfLeafNode(root));
        System.out.println(numberOfLeafNodeIterative(root));
        System.out.println(numberOfLeafNodePreOrderIterative(root));
    }
}
