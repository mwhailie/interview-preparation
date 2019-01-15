package edu.neu.companies.microsoft;

import java.util.ArrayList;
import java.util.List;

public class LCA {
    class TreeNode{
        int val;
        TreeNode parent;
        TreeNode left;
        TreeNode right;
    }

    TreeNode lowestCommonAncester(TreeNode root, TreeNode p, TreeNode q){
        TreeNode p1 = p,  p2 = q;
        while(p1 != p2){
            p1 = p1 == root ? p2 : p1.parent;
            p2 = p2 == root ? p1 : p2.parent;
        }
        return p1;
    }

    TreeNode lowestCommonAncesterII(TreeNode root, TreeNode p, TreeNode q){
        List<TreeNode> l1 = pathFromRoot(p);
        List<TreeNode> l2 = pathFromRoot(q);
        TreeNode lca = null;
        int i = 0;
        while(i < l1.size() && i < l2.size() ){
            if(l1.get(i) != l2.get(i)){
                return l1.get(i - 1);
            }
            i ++;
        }
        return lca;
    }

    private List<TreeNode> pathFromRoot(TreeNode p) {
        List<TreeNode> res = new ArrayList<>();
        while(p != null){
            res.add(p);
        }
        return res;
    }

    TreeNode lowestCommonAncesterWithoutParent(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncesterWithoutParent(root.left, p, q);
        TreeNode right = lowestCommonAncesterWithoutParent(root.right, p, q);
        if(left != null && right != null){
            return root;
        }
        if(left != null){
            return left;
        }
        if(right != null){
            return right;
        }
        return null;
    }
}
