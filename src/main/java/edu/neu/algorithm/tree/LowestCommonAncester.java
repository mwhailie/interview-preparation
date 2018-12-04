package edu.neu.algorithm.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LowestCommonAncester {
    static class TreeNodeWithParent{
        int val;
        TreeNodeWithParent left;
        TreeNodeWithParent right;
        TreeNodeWithParent parent;

        public TreeNodeWithParent(int val) {
            this.val = val;
        }
    }
    static public int shortestPathBetweenNodes(TreeNodeWithParent q, TreeNodeWithParent p){
        if(q.equals(p)){
            return 0;
        }
        Queue<TreeNodeWithParent> queue1 = new LinkedList<>();
        queue1.offer(q);
        Queue<TreeNodeWithParent> queue2 = new LinkedList<>();
        queue2.offer(p);
        Set<TreeNodeWithParent> visited = new HashSet<>();
        TreeNodeWithParent ancestor = null;
        while (!queue1.isEmpty() && !queue2.isEmpty()){
            TreeNodeWithParent node1 = queue1.poll();
            TreeNodeWithParent node2 = queue2.poll();
            System.out.println(node1.val+ " " +node2.val);
            if(visited.contains(node1)){
                ancestor = node1;
                break;
            }
            if(visited.contains(node2)){
                ancestor = node1;
                break;
            }
            visited.add(node1);
            visited.add(node2);
            if(node1.parent != null){
                queue1.add(node1.parent);
            }else{
                ancestor = node1;
                break;
            }
            if(node2.parent != null){
                queue2.add(node2.parent);
            }else{
                ancestor = node2;
                break;
            }
        }
        if(ancestor == null){
            return -1;
        }
        int lengthToQ = findPathLength(ancestor, q);
        int lengthToP = findPathLength(ancestor, p);
        return lengthToQ + lengthToP;
    }

    static  private int findPathLength(TreeNodeWithParent ancestor, TreeNodeWithParent q) {
        if(q.equals(ancestor)){
            return 0;
        }
        Queue<TreeNodeWithParent> queue = new LinkedList<>();
        queue.offer(ancestor);
        int level = 0;
        while (!queue.isEmpty()){

            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNodeWithParent node = queue.poll();
                if(node.val==q.val){
                    return level;
                }
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            level++;
        }
        return level;
    }
    public static void main(String[] args) {
        TreeNodeWithParent root = new TreeNodeWithParent(1);
        root.left = new TreeNodeWithParent(2);
        root.left.parent = root;
        root.right = new TreeNodeWithParent(3);
        root.right.parent = root;
        root.left.left = new TreeNodeWithParent(4);
        root.left.left.parent = root.left;
        root.left.right = new TreeNodeWithParent(5);
        root.left.right.parent = root.left;
        System.out.println(shortestPathBetweenNodes(root.left.left , root.right));
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
