package edu.neu.companies.microsoft;

import edu.neu.algorithm.tree.TreeNode;

import java.util.*;

public class PrintTree {
    public List<List<String>> printTree(TreeNode root) {
        List<String> list = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        int h = getHeight(root);
        if (h == 1) {
            list.add("" + root.value);
            res.add(list);
            return res;
        }
        for (int i = 0; i < h; i++)
            list = new ArrayList<>();
        for (int j = 0; j < Math.pow(h, 2) - 1; j++) {
            list.add("");
        }
        res.add(list);

        set(root, res, 0, list.size() / 2);
        return res;
    }
    public void set(TreeNode root, List<List<String>> res, int depth, int pos) {
        if(root == null){
            return;
        }
        List<String> list = res.get(depth);
        list.set(pos, "" + root.value);
        set(root.left, res, depth + 1, pos / 2);
        set(root.right, res, depth + 1, pos / 2 + 1);
    }
    public int getHeight(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}
