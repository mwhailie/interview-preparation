package edu.neu.algorithm.list;

public class ReverseInPair {
    public ListNode swapPairs(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        head = fakeHead;
        while(head != null){
            head = reverseNextKGroup(head);
            if(head == null){
                break;
            }
        }
        return fakeHead.next;
    }
    // head->next->third->tail
    // head->third->next->tail
    public ListNode reverseNextKGroup(ListNode head) {
        if(head == null|| head.next == null || head.next.next == null){
            return null;
        }
        ListNode next = head.next;
        ListNode third = next.next;
        ListNode tail = third.next;

        third.next = next;
        head.next = third;
        next.next = tail;
        return next;
    }
}
