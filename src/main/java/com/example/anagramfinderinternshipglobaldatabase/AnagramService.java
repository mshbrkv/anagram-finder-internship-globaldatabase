package com.example.anagramfinderinternshipglobaldatabase;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AnagramService {


    public Map<String, List<String>> findAnagrams(List<String> words) {

        return words.stream()
                    .collect(Collectors.groupingBy(this::sortChars, TreeMap::new, sortedListCollector()));

    }

    public void printAnagrams(Map<String, List<String>> anagrams) {

        anagrams.values().forEach(group -> {
            System.out.println(String.join(" ", group));
        });
    }

    private String sortChars(String word) {

        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private Collector<String, ?, List<String>> sortedListCollector() {

        return Collectors.collectingAndThen(
                Collectors.toCollection(TreeSet::new),
                ArrayList::new
        );
    }


}
