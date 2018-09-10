package edu.neu.practice.algorithm.tree;

import java.util.Stack;

public class GraphToValidTree {
    /*
    1：二叉树多一条边变成graph了，找到并删去
    follow up1: 多了好多边，删到tree property满足为止
    follow up2: 打印所有多余边
*/
    public class Solution{
        public void delete(TreeNode root){
            if(root == null){
                return;
            }
            Stack<TreeNode> s = new Stack<>();
            s.push(root);
            while(!s.isEmpty()){
                TreeNode node = s.pop();
                if(node.right != null){
                    if(!s.contains(node.right)){
                        s.push(node.right);
                    }else{
                        node.right = null;
                    }
                }
                if(node.left != null){
                    if(!s.contains(node.left)){
                        s.push(node.left);
                    }else{
                        node.left = null;
                    }
                }
            }
        }
    }

}
