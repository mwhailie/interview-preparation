package edu.neu.algorithm.tree;

public class MaxPathSum {
    /*
        题目描述提到卫星传播信号啊什么的无关紧要。其实就是给你一棵树，从每一个node传播信号到它的多个children会有不同的时间。
        问把一个信号从这棵树的root传播到所有的node要多长时间。就是maximum path sum吧。用dfs搞定了。
     */
    public int maxPathSum(TreeNode root) {
        int[] globalMax = new int[]{Integer.MIN_VALUE};
        longestPathSumFromThisNode(root, globalMax);
        return globalMax[0];
    }
    public int longestPathSumFromThisNode(TreeNode node, int[] globalMax){
        if(node == null){
            return 0;
        }
        int left = Math.max(0, longestPathSumFromThisNode(node.left, globalMax));
        int right = Math.max(0, longestPathSumFromThisNode(node.right, globalMax));
        globalMax[0] = Math.max(globalMax[0], left + right + node.value);
        return node.value + Math.max(left, right);
    }
}