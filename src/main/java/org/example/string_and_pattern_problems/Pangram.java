package org.example.string_and_pattern_problems;

import java.util.Arrays;

public class Pangram {
    public static String findMissingLetters(String sentence){
        sentence = sentence.replaceAll("[^A-Za-z]","");
        sentence = sentence.toLowerCase();
        int[] alphabets = new int[26];
        Arrays.fill(alphabets,0);
        for(char c : sentence.toCharArray())
            alphabets[c-'a'] = 1;

        StringBuilder result = new StringBuilder();
        for(int i = 0;i<alphabets.length;i++){
            if(alphabets[i] != 1)
                result.append((char)('a'+i));
        }
        return result.toString();
    }

    static void main() {
        System.out.println(findMissingLetters("The slow purple oryx meanders past the quiescent canine"));
    }
}
