package edu.neu.practice.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraverse {

    public List<Integer> inOrderTraversalByRecursion(TreeNode root){
        if(root == null){
            return null;
        }
        List<Integer> res = new ArrayList<>();
        inOrderTraversalByRecursion(root, res);
        return res;
    }
    private void inOrderTraversalByRecursion(TreeNode root, List<Integer> res){
        if(root == null){
            return;
        }
        inOrderTraversalByRecursion(root.left);
        res.add(root.value);
        inOrderTraversalByRecursion(root.right);
    }

    public List<Integer> inOrderTraversalByNonRecursion(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            res.add(node.value);
            if(node.right == null) {
                node = stack.pop();
                while(!stack.isEmpty() && stack.peek().right == node){
                    node = stack.pop();
                }
            }else{
                node = node.right;
                while(node != null){
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        return res;
    }
    public List<Integer> inOrderTraversalByNonRecursion2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.value);
            if(node.right != null) {
                node = node.right;
                while(node != null){
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        return res;
    }

    public List<Integer> inOrderTraversalByNonRecursion3(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            node = stack.pop();
            res.add(node.value);
            node = node.right;
        }
        return res;
    }

    public class BSTIterator {

        TreeNode root = null;
        Stack<TreeNode> s ;
        public BSTIterator(TreeNode root) {
            if(root != null){
                this.root = new TreeNode(root.value);
            }
            s = new Stack<>();
            buildStack(root, s);
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !s.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode mostLeft = s.pop();
            buildStack(mostLeft.right, s);
            return mostLeft.value;
        }
        private void buildStack(TreeNode root, Stack<TreeNode> s){
            while(root != null){
                s.push(root);
                root = root.left;
            }
        }
    }
//       1
//      /\
//     2  3
//     /\
//    4  5


}
