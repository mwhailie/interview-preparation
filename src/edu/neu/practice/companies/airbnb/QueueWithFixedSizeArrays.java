package edu.neu.practice.companies.airbnb;

/*
    http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=279191
 */
public class QueueWithFixedSizeArrays {

    class ListNode{
        ListNode next;
        int[] fixedSizeArray;
        int index = 0;
        public ListNode(int size) {
            fixedSizeArray = new int[size];
        }
    }

    ListNode head;
    ListNode tail;
    int headIndex;
    int size;
    int capacity;

    public QueueWithFixedSizeArrays(int capacity) {
        this.size = 0;
        this.headIndex = 0;
        this.capacity = capacity;
        this.head = new ListNode(capacity);
        this.tail = head;
    }

    public void offer(int i){
        if(tail.index == capacity - 1){
            ListNode node = new ListNode(size);
            tail.next = node;
            tail = node;
        }
        tail.fixedSizeArray[tail.index ++] = i;
        size ++;
    }

    public Integer poll(){
        if(size == 0){
            return null;
        }
        if(head.index == headIndex && head != tail ){
            head = head.next;
            headIndex = 0;
        }
        int res = head.fixedSizeArray[headIndex];
        headIndex ++ ;
        size --;
        return res;
    }

    public int size(){
        return size;
    }

    public static void main(String args[])
    {
        QueueWithFixedSizeArrays queue = new QueueWithFixedSizeArrays(10);
        System.out.println(queue.poll());//null
        queue.offer(1);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        queue.offer(4);
        queue.offer(5);
        queue.offer(6);

        System.out.println(queue.poll());//1
        System.out.println(queue.poll());//1
        System.out.println(queue.poll());//2
        System.out.println(queue.poll());//3
        System.out.println(queue.poll());//4
        queue.offer(7);
        System.out.println("size: " + queue.size());//size:3
        System.out.println(queue.poll());//5
        System.out.println(queue.poll());//6
        System.out.println(queue.poll());//7
        System.out.println(queue.poll());//null
    }
}
