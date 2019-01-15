package edu.neu.companies.microsoft;

import edu.neu.algorithm.tree.TreeNode;

public class LCAInBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode lca = null;
        if(root.value > p.value && root.value > q.value){
            lca = lowestCommonAncestor(root.left, p, q);
        }else if(root.value < p.value && root.value < q.value){
            lca = lowestCommonAncestor(root.right, p, q);
        }
        return lca;
    }
}