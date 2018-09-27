package edu.neu.practice.algorithm.tree;

import java.util.*;

public class TwoSumInBST {
//    TwoSumInBST
//    Set -> O(n) space
//    Search (k - node.value) for each node -> O(n^2) Time
//    How to do it O(N) time, less than O(n) space?
//    -- Think about binary search in a sorted array
//    -- How to move pointer in a BST?
//    ---- Inorder Traverse with stack, not recursive
//    Space for stack : O(logN)/O(h) where h is height of the tree

    public boolean findTarget(TreeNode root, int k) {
        Stack<TreeNode> left = new Stack<>();
        buildStack(root, true, left);
        Stack<TreeNode> right = new Stack<>();
        buildStack(root, false, right);
        while(left.peek().value < right.peek().value){
            int sum = left.peek().value + right.peek().value;
            if(sum > k){
                moveLeft(right);
            }else if(sum < k){
                moveRight(left);
            }else{
                return true;
            }
        }
        return false;
    }
    private void moveLeft(Stack<TreeNode> right){
        TreeNode mostRight = right.pop();
        buildStack(mostRight.left, false, right);
    }
    private void moveRight(Stack<TreeNode> left){
        TreeNode mostLeft = left.pop();
        buildStack(mostLeft.right, true, left);
    }
    private void buildStack(TreeNode root, boolean leanLeft, Stack<TreeNode> s){
        while(root != null){
            s.push(root);
            if(leanLeft){
                root = root.left;
            }else{
                root = root.right;
            }
        }
    }

}
