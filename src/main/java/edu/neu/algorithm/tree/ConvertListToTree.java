package edu.neu.algorithm.tree;

import edu.neu.algorithm.list.ListNode;

public class ConvertListToTree {
    //O(NlogN)
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }
        TreeNode root = toBST(nums, 0, nums.length - 1);
        return root;
    }

    public TreeNode toBST(int[] nums, int low, int high){
        if(low > high){
            return null;
        }
        int mid = low + (high - low)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = toBST(nums, low, mid - 1);
        node.right = toBST(nums, mid + 1, high);
        return node;
    }
    //O(N^2)
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if(prev != null){
            prev.next = null;
        }else{
            head = null;
        }
        TreeNode root = new TreeNode(slow.value);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }
}
