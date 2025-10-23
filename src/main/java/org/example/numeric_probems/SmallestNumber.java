package org.example.numeric_probems;

public class SmallestNumber {
    public static int findMin(int[] a) {
        int low = 0;
        int high = a.length-1;
        while(low<high) {
            int mid = (low+high)/2;
            if(a[mid] > a[high]){
                low = mid + 1;
            }else {
                high = mid;
            }
        }
        return a[low];
    }

    static void main() {
        System.out.println(findMin(new int[] {3,4,5,6,7,8,9,1,2}));
    }
}
