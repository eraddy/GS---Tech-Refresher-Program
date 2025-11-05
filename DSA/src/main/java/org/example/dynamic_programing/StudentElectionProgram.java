package org.example.dynamic_programing;

public class StudentElectionProgram {
    public static int whoIsElected(int n, int k) {
        if (n == 1) {
            return 1;
        }
        return (whoIsElected(n - 1, k) + k - 1) % n + 1;
    }

    public static void main(String[] args) {
        System.out.println(whoIsElected(1, 1)); // 1
        System.out.println(whoIsElected(2, 2)); // 1
        System.out.println(whoIsElected(4, 2)); // 1
        System.out.println(whoIsElected(5, 3)); // 4 (extra test)
    }
}
