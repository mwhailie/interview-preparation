package edu.neu.algorithm.tree;

public class BinarySearchTree {
    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        public TreeNode(int value){
            this.value = value;
        }
    }

    public TreeNode insert(TreeNode root, int value){
        if(root == null){
            root = new TreeNode(value);
            return root;
        }
        if(root.value > value){
            root.left = insert(root.left, value);
        }else if(root.value > value){
            root.right = insert(root.right, value);
        }
        return root;
    }
    public TreeNode get(TreeNode root, int value){
        if(root == null){
            return null;
        }
        if(root.value == value){
            return root;
        }else if(root.value > value){
            return get(root.left, value);
        }else if(root.value > value){
            return get(root.right, value);
        }
        return null;
    }
//  Leetcode 450 https://leetcode.com/problems/delete-node-in-a-bst/description/
    public TreeNode delete(TreeNode root, int value){
        if(root == null){
            return null;
        }
        if(root.value > value){
            root.left  = delete(root.left, value);
        }else if(root.value < value){
            root.right  = delete(root.right, value);
        }else{
            if(root.left == null && root.right == null){
                return null;
            }else if(root.left != null && root.right == null){
                return root.left;
            }else if(root.left == null && root.right != null){
                return root.right;
            }else{
                TreeNode t = root;
                root = findMin(t.right);
                root.right = deleteMin(t.right);
                root.left = t.left;
            }
        }
        return  root;
    }

    public TreeNode findMin(TreeNode root){
        if(root == null){
            return  null;
        }
        while(root.left != null){
            root = root.left;
        }
        return root;
    }
    public TreeNode deleteMin(TreeNode root){
        if (root.left == null) return root.right;
        root.left = deleteMin(root.left);
        return root;
    }

    //  Leetcode 98 https://leetcode.com/problems/validate-binary-search-tree/description/
    public boolean isBST(TreeNode root){
        if (root == null) return true;
        return isBST(root, null, null);
    }

    private boolean isBST(TreeNode root, Integer minValue, Integer maxValue) {
        if (root == null) return true;
        if(minValue != null  && root.value <= minValue || maxValue != null  && root.value >= maxValue){
            return false;
        }
        return isBST(root.left, minValue, root.value) && isBST(root.right, root.value, maxValue);
    }
}
