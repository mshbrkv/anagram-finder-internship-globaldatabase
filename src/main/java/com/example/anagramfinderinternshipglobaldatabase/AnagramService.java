package com.example.anagramfinderinternshipglobaldatabase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnagramService {



    public Map<String, List<String>> findAnagrams(List<String> words) {

        return words.stream()
                    .collect(Collectors.groupingBy(word -> {
                        char[] chars = word.toCharArray();
                        Arrays.sort(chars);
                        return new String(chars);
                    }));
    }

    public void printAnagrams(Map<String, List<String>> anagrams) {

        anagrams.values().forEach(group -> {
            System.out.println(String.join(" ", group));
        });
    }

}
