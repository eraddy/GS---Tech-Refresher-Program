package org.example.mathematics_problems;

public class PowerOfExpo {
    public static double power(double base,int exp)
    {
        if(exp == 0)
            return 1;

        if(exp < 0)
            return 1/power(base,-exp);

        double half = power(base,exp/2);

        if(exp % 2 == 0)
            return half*half;
        else
            return base*half*half;
    }

    static void main() {
        double ans = power(2.0,4);
        System.out.println(ans);
    }
}
