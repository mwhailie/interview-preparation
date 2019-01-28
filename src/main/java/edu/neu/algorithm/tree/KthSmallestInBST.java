package edu.neu.algorithm.tree;

public class KthSmallestInBST {
    public int kthSmallest(TreeNode root, int k) {
        int left = count(root.left);
        if(left + 1 == k){
            return root.value;
        }else if(left + 1 > k){
            return kthSmallest(root.left, k);
        }else{
            return kthSmallest(root.right, k - left - 1);
        }
    }
    public int count(TreeNode root){
        if(root == null) return 0;
        return count(root.left) + count(root.right) + 1;
    }
}
