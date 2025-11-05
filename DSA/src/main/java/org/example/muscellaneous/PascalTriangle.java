package org.example.muscellaneous;

public class PascalTriangle {
    public static int pascal(int col, int row) {
        // Base case
        if (col == 0 || col == row) {
            return 1;
        }

        // Efficient iterative calculation of C(row, col)
        long result = 1;
        for (int i = 1; i <= col; i++) {
            result = result * (row - i + 1) / i;
        }

        return (int) result;
    }
}
