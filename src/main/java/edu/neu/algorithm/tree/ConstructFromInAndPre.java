package edu.neu.algorithm.tree;

public class ConstructFromInAndPre {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) return null;
        int size = preorder.length;
        TreeNode root = buildTree(preorder, 0, inorder, 0, size - 1);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int preStart, int[] inorder, int j, int k) {
        if( j > k){
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int r = j;
        for(; r <= k; r ++){
            if(inorder[r] == preorder[preStart]){
                break;
            }
        }
        int numOfLeft = r - j;
        root.left = buildTree(preorder, preStart + 1, inorder, j, r - 1);
        root.right = buildTree(preorder, preStart + (r - j) + 1, inorder, r + 1, k);
        return root;
    }
}
