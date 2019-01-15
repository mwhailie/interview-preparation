package edu.neu.companies.microsoft;

import edu.neu.algorithm.tree.TreeNode;

public class DiameterOfTree {

    int max = 0;
    int diameterOfTree(TreeNode root){
        if(root == null){
            return 0;
        }
        maxHeight(root);
        return max;
    }

    int maxHeight(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = maxHeight(root.left);
        int right = maxHeight(root.right);
        max = Math.max(max, left + right + 1);
        return Math.max(left, right) + 1;
    }

}
