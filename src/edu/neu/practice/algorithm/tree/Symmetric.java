package edu.neu.practice.algorithm.tree;

public class Symmetric {
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return isSymmetric(root.left, root.right);
    }
    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if(left == null && right == null){
            return true;
        }
        if(left == null || right == null){
            return false;
        }
        return left.value == right.value && isSymmetric(left.right, right.left) && isSymmetric(left.left, right.right);
    }


    // follow up : what if TreeNode has a mid child?
    public class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode mid;
        public TreeNode(int value){
            this.value = value;
        }
    }
    public boolean isSymmetric2(TreeNode root) {
        if(root == null){
            return true;
        }
        return isSymmetric2(root.left, root.right) && isSymmetric2(root.mid);
    }
    public boolean isSymmetric2(TreeNode left, TreeNode right) {
        if(left == null && right == null){
            return true;
        }
        if(left == null || right == null){
            return false;
        }
        return left.value == right.value && isSymmetric2(left.right, right.left) && isSymmetric2(left.left, right.right)
                && isSymmetric2(left.mid, right.mid);
    }
    // follow up 2: what if you know that asynmmetric happens in lower level (near the root)?
    // level order traverse, is level synmmetric?(Palindrome)
}
