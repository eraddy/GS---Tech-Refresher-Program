package org.example.mathematics_problems;

public class AddFractions {
    public static int[] addFractions(int[] fraction1, int[] fraction2)
    {
        int numerator = fraction1[0] * fraction2[1] + fraction1[1] * fraction2[0];
        int denominator = fraction1[1] *  fraction2[1];

        int gcd = gcd(Math.abs(numerator),Math.abs(denominator));

        numerator /= gcd;
        denominator /= gcd;

        if(denominator < 0){
            numerator -= numerator;
            denominator -= denominator;
        }
        return new int[] {numerator,denominator};
    }
    private static int gcd(int a, int b)
    {
        if(b==0) return a;
        return gcd(b, a % b);
    }

    static void main() {
        int [] fraction1 = new int[] {2,3};
        int [] fraction2 = new int[] {1,2};

        int[] ans = addFractions(fraction1,fraction2);
        for(int i : ans)
            System.out.print(i +" ");
    }
}
