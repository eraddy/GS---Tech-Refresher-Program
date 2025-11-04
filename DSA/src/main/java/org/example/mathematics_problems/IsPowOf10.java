package org.example.mathematics_problems;

public class IsPowOf10 {
    public static boolean isPowOf10(int x)
    {
        while (x > 10)
            x /= 10;

       return x == 1;
    }

    static void main() {
        System.out.println(isPowOf10(3));
    }
}
