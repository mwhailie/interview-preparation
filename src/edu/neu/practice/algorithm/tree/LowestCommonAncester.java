package edu.neu.practice.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

public class LowestCommonAncester {
    class TreeNodeWithParent{
        int val;
        TreeNodeWithParent left;
        TreeNodeWithParent right;
        TreeNodeWithParent parent;
    }
    public int shortestPathBetweenNodes(TreeNodeWithParent q, TreeNodeWithParent p){
        if(q.equals(p)){
            return 0;
        }
        Queue<TreeNodeWithParent> queue = new LinkedList<>();
        queue.offer(q);
        int level = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i ++){
                TreeNodeWithParent node = queue.poll();
                if(node == p){
                    return level;
                }
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
                if(node.parent != null){
                    queue.add(node.parent);
                }
            }
            level ++;
        }
        return level;
    }
    public TreeNode lowestCommonAncester(TreeNode root, TreeNode q, TreeNode p){
        if(root == null){
            return  null;
        }
        if(root == q || root == p){
            return root;
        }
        TreeNode left = lowestCommonAncester(root.left, q, p);
        TreeNode right = lowestCommonAncester(root.right, q, p);
        if(left != null && right == null){
            return  root;
        }else if(left != null){
            return left;
        }else if(right != null){
            return right;
        }else {
            return null;
        }
    }
}
