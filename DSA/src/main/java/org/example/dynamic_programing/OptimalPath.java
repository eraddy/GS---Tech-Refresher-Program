package org.example.dynamic_programing;

public class OptimalPath {
    public static Integer optimalPath(Integer[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        // Start from bottom-left (So_Cal)
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                int fromSouth = (i + 1 < m) ? dp[i + 1][j] : 0;
                int fromWest = (j - 1 >= 0) ? dp[i][j - 1] : 0;
                dp[i][j] = grid[i][j] + Math.max(fromSouth, fromWest);
            }
        }

        // Destination is top-right corner (New_York)
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        Integer[][] grid = {
                {0, 0, 0, 0, 5},
                {0, 1, 1, 1, 0},
                {2, 0, 0, 0, 0}
        };

        System.out.println(optimalPath(grid)); // Expected output: 10
    }
}
