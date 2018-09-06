package edu.neu.practice.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreorderTraverse {
    public List<Integer> preOrderTraversalByRecursion(TreeNode root){
        if(root == null){
            return null;
        }
        List<Integer> res = new ArrayList<>();
        preOrderTraversalByRecursion(root, res);
        return res;
    }

    private void preOrderTraversalByRecursion(TreeNode root, List<Integer> res){
        if(root == null){
            return;
        }
        res.add(root.value);
        preOrderTraversalByRecursion(root.left);
        preOrderTraversalByRecursion(root.right);
    }
//DFS
    public List<Integer> preOrderTraversalByNonRecursion(TreeNode root){
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
}
