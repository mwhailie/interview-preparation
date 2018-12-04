package edu.neu.algorithm.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraverse {

    public static List<Integer> inOrderTraversalByRecursion(TreeNode root){
        if(root == null){
            return null;
        }
        List<Integer> res = new ArrayList<>();
        inOrderTraversalByRecursion(root, res);
        return res;
    }
    private static void inOrderTraversalByRecursion(TreeNode root, List<Integer> res){
        if(root == null){
            return;
        }
        inOrderTraversalByRecursion(root.left, res);
        res.add(root.value);
        inOrderTraversalByRecursion(root.right, res);
    }

    public static List<Integer> inOrderTraversalByIterative(TreeNode root){
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
    public static List<Integer> inOrderTraversalByIterative2(TreeNode root){
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

    public static List<Integer> inOrderTraversalByIterative3(TreeNode root){
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.add(node.value);
            node = node.right;
        }
        return res;
    }

    public static class BSTIterator {

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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(inOrderTraversalByRecursion(root));
        System.out.println(inOrderTraversalByIterative(root));
        System.out.println(inOrderTraversalByIterative2(root));
        System.out.println(inOrderTraversalByIterative3(root));
        BSTIterator b = new BSTIterator(root);
        while(b.hasNext()){
            System.out.print(b.next() + " ");
        }
        root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(5);
        System.out.println(inOrderTraversalByRecursion(root));
        System.out.println(inOrderTraversalByIterative(root));
        System.out.println(inOrderTraversalByIterative2(root));
        System.out.println(inOrderTraversalByIterative3(root));
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.left.left.left.left = new TreeNode(5);
        System.out.println(inOrderTraversalByRecursion(root));
        System.out.println(inOrderTraversalByIterative(root));
        System.out.println(inOrderTraversalByIterative2(root));
        System.out.println(inOrderTraversalByIterative3(root));
        b = new BSTIterator(root);
        while(b.hasNext()){
            System.out.print(b.next() + " ");
        }
    }

}
