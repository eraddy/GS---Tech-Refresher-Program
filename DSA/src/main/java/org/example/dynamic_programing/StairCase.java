package org.example.dynamic_programing;

public class StairCase {
    public static Integer countSteps(Integer n) {
        if (n == null || n < 0) return 0;
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] dp = new int[n + 1];
        dp[0] = 1;  // 1 way (stay)
        dp[1] = 1;  // (1)
        dp[2] = 2;  // (1+1, 2)

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(countSteps(3));   // 4
        System.out.println(countSteps(1));   // 1
        System.out.println(countSteps(2));   // 2
        System.out.println(countSteps(10));  // 274
        System.out.println(countSteps(-5));  // 0
    }
}
