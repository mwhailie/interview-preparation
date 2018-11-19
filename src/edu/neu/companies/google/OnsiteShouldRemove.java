package edu.neu.companies.google;

import java.util.*;
import java.util.function.Predicate;

/*
    You are given a TreeNode and a Predicate<TreeNode> shouldRemove Interface.
    If shouldRemove.test(node) returns true, remove node from the tree and split the tree into 2 trees.
    Return the result, a list of TreeNode, after all node that should be remove are removed.
 */
public class OnsiteShouldRemove {
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        public TreeNode(int value){
            this.value = value;
        }
    }

    public List<TreeNode> removeTreeNode(TreeNode root, Predicate<TreeNode> shouldRemove){
        List<TreeNode> res = new ArrayList<>();
        res.add(root);
        traverse(root, shouldRemove, res);
        return res;
    }

    private void traverse(TreeNode root, Predicate<TreeNode> shouldRemove, List<TreeNode> res) {
        if(root == null){
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        if(shouldRemove.test(root)){
            if(res.contains(root)){
                res.remove(root);
            }
            root.left = null;
            root.right = null;
            if(left != null){
                res.add(left);
            }
            if(right != null) {
                res.add(right);
            }
        }
        traverse(left, shouldRemove, res);
        traverse(right, shouldRemove, res);
    }

    public List<TreeNode> removeTreeNodeII(TreeNode root, Predicate<TreeNode> shouldRemove){
        List<TreeNode> res = new ArrayList<>();
        traverseII(root, shouldRemove, res, true);
        return res;
    }
    private TreeNode traverseII(TreeNode node, Predicate<TreeNode> shouldRemove, List<TreeNode> res, boolean removeParent) {
        if(node == null){
            return null;
        }
        boolean removeThis = shouldRemove.test(node);
        if(removeParent & !removeThis){
            res.add(node);
        }
        node.left = traverseII(node.left, shouldRemove, res, removeThis);
        node.right = traverseII(node.right, shouldRemove, res, removeThis);
        return removeThis? null : node;
    }

    /*
        Follow up: what if the tree is immutable and you can not modify it?
     */
    public static class ImmutableTreeNode {
        int value;
        final ImmutableTreeNode left;
        final ImmutableTreeNode right;
        public ImmutableTreeNode(int value, ImmutableTreeNode left, ImmutableTreeNode right){
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public List<ImmutableTreeNode> removeTreeNodeIII(ImmutableTreeNode root, Predicate<ImmutableTreeNode> shouldRemove){
        List<ImmutableTreeNode> res = new ArrayList<>();
        res.add(root);
        traversIII(root, shouldRemove, res);
        return res;
    }
    private void traversIII(ImmutableTreeNode root, Predicate<ImmutableTreeNode> shouldRemove, List<ImmutableTreeNode> res) {
        if(root == null){
            return;
        }
        ImmutableTreeNode left = root.left;
        ImmutableTreeNode right = root.right;
        if(shouldRemove.test(root)){
            if(res.contains(root)){
                res.remove(root);
            }
            if(left != null){
                res.add(left);
            }
            if(right != null) {
                res.add(right);
            }
        }
        traversIII(left, shouldRemove, res);
        traversIII(right, shouldRemove, res);
    }
    private ImmutableTreeNode copyOf(ImmutableTreeNode root) {
        if(root == null){
            return null;
        }
        ImmutableTreeNode newNode =  new ImmutableTreeNode(root.value, copyOf(root.left), copyOf(root.right));
        return newNode;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        OnsiteShouldRemove o = new OnsiteShouldRemove();
        List<TreeNode> res = o.removeTreeNode(root, new Predicate<TreeNode>() {
            @Override
            public boolean test(TreeNode treeNode) {
                if(treeNode.value == 1 || treeNode.value == 4){
                    return true;
                }
                return false;
            }
        });
        System.out.println("I :");
        for (TreeNode node : res){
            System.out.print(node.value + ", ");
        }
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        List<TreeNode> res2 = o.removeTreeNodeII(root, new Predicate<TreeNode>() {
            @Override
            public boolean test(TreeNode treeNode) {
                if(treeNode.value == 1 || treeNode.value == 4){
                    return true;
                }
                return false;
            }
        });
        System.out.println("II :");
        for (TreeNode node : res2){
            System.out.print(node.value + ", ");
        }
    }
}
