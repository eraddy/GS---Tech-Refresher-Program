package org.example.dynamic_programing;

public class WalkingRobot {
    public static Integer[] walk(String path) {
        int x = 0, y = 0;

        if (path == null || path.isEmpty()) {
            return new Integer[]{x, y};
        }

        for (char c : path.toCharArray()) {
            switch (c) {
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
                case 'L':
                    x--;
                    break;
                case 'R':
                    x++;
                    break;
                default:
                    // ignore invalid characters
                    break;
            }
        }

        return new Integer[]{x, y};
    }

    public static void main(String[] args) {
        // ✅ Test Cases
        printResult(walk(""));        // [0, 0]
        printResult(walk("L"));       // [-1, 0]
        printResult(walk("UUU"));     // [0, 3]
        printResult(walk("ULDR"));    // [0, 0]
        printResult(walk("UUDDLRLR")); // [0, 0] (extra test)
    }

    private static void printResult(Integer[] result) {
        System.out.println("[" + result[0] + ", " + result[1] + "]");
    }
}
