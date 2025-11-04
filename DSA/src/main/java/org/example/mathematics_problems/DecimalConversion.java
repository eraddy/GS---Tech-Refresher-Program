package org.example.mathematics_problems;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DecimalConversion {
    public static String vulgarToDecimal(long numerator, long denominator)
    {
        if(denominator == 0) throw new IllegalArgumentException("cannot divide by 0");
        if(numerator == 0) return "0";

        StringBuilder result = new StringBuilder();

        if((numerator < 0) ^ (denominator < 0))
        {
            result.append("-");
        }

        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        long integerPart = numerator / denominator;
        result.append(integerPart);

        long remainder  = numerator % denominator;
        if(remainder == 0 ) return result.toString();

        result.append(".");

        Map<Long,Integer> index = new HashMap<>();

        StringBuilder decimalPart = new StringBuilder();
        while (remainder!=0)
        {
            if(index.containsKey(remainder)){
                int start = index.get(remainder);
                decimalPart.insert(start,"(");
                decimalPart.append(")");
                result.append(decimalPart);
                return result.toString();
            }
            index.put(remainder,decimalPart.length());
            remainder *= 10;
            long digit = remainder/denominator;
            decimalPart.append(digit);
            remainder %= denominator;
        }
        result.append(decimalPart);
        return result.toString();
    }

    static void main() {
        System.out.println(vulgarToDecimal(1L, 2L));   // 0.5
        System.out.println(vulgarToDecimal(1L, 3L));   // 0.(3)
        System.out.println(vulgarToDecimal(1L, 30L));  // 0.0(3)
        System.out.println(vulgarToDecimal(1L, 75L));  // 0.01(3)
        System.out.println(vulgarToDecimal(4L, 7L));   // 0.(571428)
    }
}
