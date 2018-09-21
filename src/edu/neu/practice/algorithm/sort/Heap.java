package edu.neu.practice.algorithm.sort;

import java.util.Random;

public class Heap {
    int[] nums;
    int size;
    public Heap(int initCapacity){
        nums = new int[initCapacity + 1];
        size = 0;
    }
    public void insert(int n){
        if(size  == nums.length - 1){
            int[] aux = new int[nums.length * 2];
            for(int i = 1; i <= size; i ++){
                aux[i] = nums[i];
            }
            nums = aux;
        }
        size++;
        nums[size] = n;
        swim(size);

    }
    public int pop(){
        int min = nums[1];
        swap(1,size --);
        nums[ size + 1] = 0;
        sink(1);
        return min;
    }
    void swim(int n){
        while(n > 1 && nums[n] < nums[n/2] ){
            swap(n, n/2);
            n /= 2;
        }
    }

    void sink(int n){
        while(n * 2 <= size){
            int j = n * 2;
            if(j < size && nums[j] > nums[j + 1]){
                j ++;
            }
            if(nums[n] <= nums[j]){
                break;
            }
            swap(n, j);
            n = j;
        }
    }
    private void swap(int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public static void main(String[] args) {
        Heap heap = new Heap(10);
        Random r = new Random();
        for(int i = 0; i < 100; i ++){
//                heap.insert(i);
            heap.insert(r.nextInt(100));
        }
        while(!heap.isEmpty()){
            System.out.println(heap.pop() + ", ");
        }
    }
}
