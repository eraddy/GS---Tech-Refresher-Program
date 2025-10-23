package org.example.numeric_probems;

import java.security.spec.RSAOtherPrimeInfo;

public class SecondSmallestElement {
    public static int secondSmallestElement(int[] x){
        if(x == null || x.length < 2)
            return 0;
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for(int i : x){
            if(i < min)
            {
                secondMin = min;
                min = i;
            }
            else if(i > min && i < secondMin)
            {
                secondMin = i;
            }
        }
        return secondMin;
    }

    static void main() {
        System.out.println(secondSmallestElement(new int[] {-1,0,1,-2,2}));
    }
}
