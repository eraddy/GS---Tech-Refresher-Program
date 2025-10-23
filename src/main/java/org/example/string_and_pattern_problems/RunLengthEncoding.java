package org.example.string_and_pattern_problems;

public class RunLengthEncoding {
    public static String rle(String input){
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<input.length();i++)
        {
            int j = i+1;
            while(j < input.length() && input.charAt(j) == input.charAt(i)) j++;
            result.append(input.charAt(i));
            result.append(j-i);
            i = j-1;
        }
        return result.toString();
    }

    static void main() {
        System.out.println(rle("aaabbbaad"));
    }
}
