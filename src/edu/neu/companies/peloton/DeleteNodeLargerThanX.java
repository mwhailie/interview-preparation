package edu.neu.companies.peloton;

public class DeleteNodeLargerThanX {

    public static class ListNode {
        public int value;
        public ListNode next;
        public ListNode(int value){
            this.value = value;
        }
    }

    public static ListNode deleteNodeLargerThanX(ListNode head, int x){
        if(head == null){
            return head;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        ListNode curr = head;
        ListNode prev = fakeHead;
        while(curr != null){
            while(curr != null && curr.value > x){
                curr = curr.next;
            }
            if(curr == null){
                prev.next = null;
                break;
            }
            prev.next = curr;
            prev = curr;
            curr = curr.next;
        }
        return fakeHead.next;
    }

    public static void main(String[] args) {
        ListNode first = new ListNode(4);
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(2);
        ListNode forth = new ListNode(3);
        ListNode fifth = new ListNode(5);
        first.next = second;
        second.next = third;
        third.next = forth;
        forth.next = fifth;
        ListNode head = deleteNodeLargerThanX(first, 3);
        while (head != null){
            System.out.println(head.value);
            head = head.next;
        }
    }
}
