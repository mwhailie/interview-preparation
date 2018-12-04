package edu.neu.companies.quora;

public class FindTargetInMountainArray {

    public int search(int[] A, int target) {
        int peak = findPeak(A);
        // System.out.print(peak);
        int hi = peak;
        int lo = 0;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(A[mid] == target){
                return mid;
            }else if(A[mid] < target){
                lo = mid + 1;
            }else{
                hi = mid;
            }
        }
        hi = A.length - 1;
        lo = peak;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(A[mid] == target){
                return mid;
            }else if(A[mid] > target){
                lo = mid + 1;
            }else{
                hi = mid;
            }
        }
        if(A[hi] == target){
            return hi;
        }else if(A[lo] == target){
            return lo;
        }else{
            return -1;
        }
    }
    public static int findPeak(int[] A){

        if(A.length == 2){
            return A[0] < A[1] ? 1 : 0;
        }
        int hi = A.length - 1;
        int lo = 0;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(A[mid - 1] < A[mid] && A[mid] > A[mid + 1]){
                return mid;
            }else if(A[mid - 1] < A[mid] && A[mid] < A[mid + 1]){
                lo = mid + 1;
            }else{
                hi = mid;
            }
        }
        return A[lo] < A[hi] ? hi : lo;
    }
}
