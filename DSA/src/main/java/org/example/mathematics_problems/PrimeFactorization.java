package org.example.mathematics_problems;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorization {
    public static List<Integer> primeFactorization(int x)
    {
        List<Integer> factors = new ArrayList<>();
        while(x % 2 == 0)
        {
            factors.add(2);
            x /= 2;
        }

        for(int i = 3; i * i <= x;i += 2)
        {
            while(x % i == 0)
            {
                factors.add(i);
                x /= i;
            }
        }

        if(x > 2)
            factors.add(x);
        return factors;
    }

    static void main() {
        int[] x = new int[] {6,5,12};
        for(int i : x)
            System.out.println(primeFactorization(i));
    }
}
