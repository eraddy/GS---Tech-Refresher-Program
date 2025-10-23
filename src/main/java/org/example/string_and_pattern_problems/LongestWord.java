package org.example.string_and_pattern_problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Dictionary {
     String[] words;

    public Dictionary(String[] words) {
        this.words = words;
    }
}


public class LongestWord {
    public static Set<String> longestWord(String letters, Dictionary dictionary)
    {
        Map<Character,Integer> countMap = getCountMap(letters);
        Set<String> result = new HashSet<>();
        int maxLength = Integer.MIN_VALUE;

        for(String word : dictionary.words){
            if(canForm(word,countMap))
            {
                int temp = word.length();
                if(temp > maxLength)
                {
                    result.clear();
                    result.add(word);
                    maxLength = temp;
                }else if(temp == maxLength)
                {
                    result.add(word);
                }
            }
        }
        return result;
    }
    private static boolean canForm(String word, Map<Character,Integer> countMap)
    {
        Map<Character,Integer> wordCountMap = getCountMap(word);
        for(Map.Entry<Character,Integer> entry : wordCountMap.entrySet()){
            char c = entry.getKey();
            int count = entry.getValue();
            if(countMap.getOrDefault(c,0) < count)
                return false;
        }
        return true;
    }
    private static Map<Character,Integer> getCountMap(String word)
    {
        Map<Character,Integer> countMap = new HashMap<>();
        for(Character c : word.toCharArray())
            countMap.put(c,countMap.getOrDefault(c,0)+1);
        return countMap;
    }


    static void main() {
        Dictionary dictionary = new Dictionary(new String[]{"to", "toe", "toes", "doe", "dog", "god", "dogs", "book", "banana"});
        Set<String> ans1 = longestWord("toe",dictionary);
        System.out.println(ans1);
        Set<String> ans2 = longestWord("oetdg",dictionary);
        System.out.println(ans2);

    }
}
