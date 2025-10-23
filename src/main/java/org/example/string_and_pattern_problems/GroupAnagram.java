package org.example.string_and_pattern_problems;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagram {
    public static Set<Set<String>> groupAnagram(List<String> words){
        return new HashSet<>(words
                .stream()
                .collect(Collectors.groupingBy(word -> getCountMap((String) word), Collectors.toSet()))
                .values());
    }
    private static Map<Character,Integer> getCountMap(String word){
        Map<Character, Integer> coutMap = new HashMap<>();
        for(Character c : word.toCharArray())
            coutMap.put(c,coutMap.getOrDefault(c,0)+1);
        return coutMap;
    }

    static void main() {
        List<String> words = new ArrayList<>();
        words.add("cat");
        words.add("god");
        words.add("dog");
        System.out.println(groupAnagram(words));
    }
}
