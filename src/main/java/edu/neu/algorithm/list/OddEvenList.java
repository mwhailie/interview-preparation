package edu.neu.algorithm.list;

public class OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        if(head == null) return head;
        ListNode evenHead = head.next;
        ListNode odd = head, even = head.next;
        while(even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }
}
