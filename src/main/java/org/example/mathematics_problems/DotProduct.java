package org.example.mathematics_problems;

public class DotProduct {
    public static long dotProduct(int[] array1, int[] array2)
    {
        if(array1.length != array2.length) return -1;
        long sum = 0;
        for(int i = 0;i<array2.length;i++){
            sum += (long) array1[i] * array2[i];
        }
        return sum;
    }
    static void main() {
        int[] array1 = new int[] {1,2};
        int[] array2 = new int[] {2,3};
        long ans = dotProduct(array1,array2);
        if(ans == -1)
            System.out.println("Invalid input for dot product");
        else
            System.out.println(ans);
    }
}
