package edu.neu.companies.quora;


import java.util.*;

public class PathSum {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if(root == null){
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        findPathFromRoot(root, target, new ArrayList<>(),res);
        // root?
        // res.addAll(findPath(root.left, target));
        // res.addAll(findPath(root.right, target));
        return res;
    }

    public static void findPathFromRoot(TreeNode root, int target, List<Integer> temp, List<List<Integer>> res){
        if(root == null){
            return;
        }
        temp.add(root.val);
        // root to leave?
        if(root.left == null && root.right == null && root.val == target){
            res.add(new ArrayList<>(temp));
        }else{
            findPathFromRoot(root.left, target - root.val, temp, res);
            findPathFromRoot(root.right, target - root.val, temp, res) ;
        }
        temp.remove(temp.size() - 1);
    }
    public static int findPathII(TreeNode root, int target){
        if(root == null){
            return 0;
        }
        return findPathFromRootII(root, target) + findPathII(root.left, target) + findPathII(root.right, target) ;
    }

    public static int findPathFromRootII(TreeNode root, int target){
        if(root == null){
            return 0;
        }
        return (root.val == target? 1: 0) + findPathFromRootII(root.left, target - root.val) + findPathFromRootII(root.right, target - root.val) ;
    }
}
