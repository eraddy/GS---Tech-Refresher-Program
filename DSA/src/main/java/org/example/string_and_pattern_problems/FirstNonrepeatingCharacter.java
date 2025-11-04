package org.example.string_and_pattern_problems;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonrepeatingCharacter {
    public static char findFirst(String input)
    {
        return input
                .chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()

                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse('0');
    }

    static void main() {
        System.out.println(findFirst("apple"));
        System.out.println(findFirst("xxyyzz"));
    }
}
