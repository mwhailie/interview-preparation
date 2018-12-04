package edu.neu.algorithm.tree;

public class DiameterOfTree {
    public  int diameterOfTree(TreeNode root){

        int[] max = new int[]{0};
        height(root, max);
        return max[0];
    }
    public int height(TreeNode root, int[] max){
        if(root == null){
            return 0;
        }
        int left = height(root.left, max);
        int right = height(root.right, max);
        max[0] = Math.max(max[0], left + right);
        return Math.max(left, right) + 1;
    }
}
