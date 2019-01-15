package edu.neu.algorithm.list;

public class reverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null){
            return null;
        }
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;
        head = fakeHead;
        while(head != null){
            head = reverseNextKGroup(head, k);
            if(head == null){
                break;
            }
        }
        return fakeHead.next;
    }
    //head->n1->n2->...n(k)
    // head->nk->n(k-1)
    public ListNode reverseNextKGroup(ListNode head, int k) {
        if(head == null){
            return null;
        }
        ListNode n1 = head.next;
        ListNode nk = head;
        for(int i = 0; i < k; i ++){
            if(nk == null){
                return null;
            }
            nk = nk.next;
        }
        if(nk == null){
            return null;
        }
        ListNode tail = nk.next;
        ListNode curr = n1, prev = null;
        while(curr != tail){
            ListNode p = curr.next;
            curr.next = prev;
            prev = curr;
            curr = p;
        }
        head.next = nk;
        n1.next = tail;
        return n1;
    }
}
