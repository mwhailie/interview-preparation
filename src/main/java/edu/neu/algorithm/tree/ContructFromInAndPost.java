package edu.neu.algorithm.tree;

public class ContructFromInAndPost {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(postorder == null || postorder.length == 0 || inorder == null || inorder.length == 0) return null;
        int size = postorder.length;
        TreeNode root = buildTree(inorder, 0, size - 1, postorder, 0, size - 1);
        return root;
    }
    public TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if(inStart > inEnd || postStart > postEnd){
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int indexOfRoot = inStart;
        for(; indexOfRoot <= inEnd; indexOfRoot ++){
            if(inorder[indexOfRoot] == postorder[postEnd]){
                break;
            }
        }
        int numOfLeft = indexOfRoot - inStart;
        root.left = buildTree(inorder, inStart, indexOfRoot - 1, postorder, postStart,  postStart + numOfLeft - 1);
        root.right = buildTree(inorder, indexOfRoot + 1, inEnd, postorder, postStart + numOfLeft, postEnd - 1);
        return root;
    }
}
