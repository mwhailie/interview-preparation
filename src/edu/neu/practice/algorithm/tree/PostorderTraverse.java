package edu.neu.practice.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostorderTraverse {
    public List<Integer> postOrderTraversalByRecursion(TreeNode root){
        if(root == null){
            return null;
        }
        List<Integer> res = new ArrayList<>();
        postOrderTraversalByRecursion(root, res);
        return res;
    }
    private void postOrderTraversalByRecursion(TreeNode root, List<Integer> res){
        if(root == null){
            return;
        }
        postOrderTraversalByRecursion(root.left);
        postOrderTraversalByRecursion(root.right);
        res.add(root.value);
    }
    public List<Integer> postOrderTraversalByNonRecursion(TreeNode root){
        if(root == null){
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            root = stack.pop();
            res.add(0, root.value);
            if(root.left != null){
                stack.push(root.left);
            }
            if(root.right != null){
                stack.push(root.right);
            }
        }
        return res;
    }
}
