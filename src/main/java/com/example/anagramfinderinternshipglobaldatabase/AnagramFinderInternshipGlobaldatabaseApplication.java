package com.example.anagramfinderinternshipglobaldatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class AnagramFinderInternshipGlobaldatabaseApplication {

    public static void main(String[] args) throws IOException {

        AnagramService fileService = new AnagramService();

        List<String> words = FileService.readFile(
                "/Users/mariabiriucova/Documents/anagram-finder-internship-globaldatabase/src/main/java/sample.txt");
        final Map<String, List<String>> anagrams = fileService.findAnagrams(words);

        fileService.printAnagrams(anagrams);

        SpringApplication.run(AnagramFinderInternshipGlobaldatabaseApplication.class, args);
    }

}
