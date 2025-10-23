package org.example.string_and_pattern_problems;

public class LongestUniformSubstring {
    public static int[] longestUniformSubString(String input){
        int len = 1;
        int start = 0;
        int maxLen = 0;
        for(int i = 1;i<input.length();i++)
        {
            if(input.charAt(i) == input.charAt(i-1)) {
                len++;
            }
            else {
                if(len > maxLen){
                    start = i - len;
                    maxLen = len;
                }
                len = 1;
            }
        }
        if(len > maxLen){
            start = input.length() - len;
            maxLen = len;
        }
        return new int[] {start,maxLen};
    }

    static void main() {
        int[] ans = longestUniformSubString("aabbbbbCdAAAAAAAAAAA");
        System.out.println(ans[0] +" "+ans[1]);
    }
}
