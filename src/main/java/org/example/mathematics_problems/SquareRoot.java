package org.example.mathematics_problems;

public class SquareRoot {
    public static double squareRoot(double x)
    {
        if(x < 0) throw new IllegalArgumentException("Cannot find the square root of 0");
        if(x == 0 || x == 1) return x;

        double low = 0,high = x;

        if(x < 1) high = 1;

        double epsilon = 1e-6;
        double mid = 0;

        while (high - low > epsilon)
        {
            mid = (high + low) / 2;
            double square = mid * mid;
            if(square > x)
                high = mid;
            else if(square < x)
                low = mid;
            else
                return mid;
        }
        return (low + high)/2;
    }

    static void main() {
        double ans = squareRoot(2);
        System.out.printf("%.5f",ans);
    }
}
