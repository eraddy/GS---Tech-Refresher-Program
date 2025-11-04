package org.example.arrays;

public class SubArrayExceedingSum {
    public static int subArrayExceedsSum(int[] arr, int target) {
        int start = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        for(int end = 0;end < arr.length;end++){
            sum+=arr[end];

            while(sum>=target){
                minLen = Math.min(minLen,end-start+1);
                sum-=arr[start];
                start++;
            }
        }
        return (minLen == Integer.MAX_VALUE) ? -1 : minLen;
    }

    static void main() {
        int[] arr = new int[] {1,2,3,4};
        int k = 6;
        System.out.println(subArrayExceedsSum(arr,k));
    }
}
