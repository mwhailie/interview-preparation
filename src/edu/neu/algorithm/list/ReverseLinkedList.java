package edu.neu.algorithm.list;

public class ReverseLinkedList {
    public ListNode reverseLinkedList(ListNode head){
        if(head == null){
            return head;
        }
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    public ListNode reverseLinkedListRecursive(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode newNode= reverseLinkedListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }
}
