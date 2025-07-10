package com.example.anagramfinderinternshipglobaldatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AnagramServiceTest {

    private AnagramService anagramService;

    @BeforeEach
    void setUp() {
        anagramService = new AnagramService();
    }

    @Test
    void testFindAnagrams_withValidInput() {
        List<String> input = Arrays.asList("act", "cat", "tree", "race", "acre", "care", "bee");

        Map<String, List<String>> result = anagramService.findAnagrams(input);

        assertEquals(4, result.size());
        assertTrue(result.values().stream().anyMatch(group -> group.containsAll(List.of("act", "cat"))));
        assertTrue(result.values().stream().anyMatch(group -> group.containsAll(List.of("race", "acre", "care"))));
        assertTrue(result.values().stream().anyMatch(group -> group.contains("tree")));
        assertTrue(result.values().stream().anyMatch(group -> group.contains("bee")));
    }

    @Test
    void testFindAnagrams_withDuplicates() {
        List<String> input = Arrays.asList("cat", "act", "cat", "tac");

        Map<String, List<String>> result = anagramService.findAnagrams(input);

        assertEquals(1, result.size());
        List<String> group = result.values().iterator().next();
        assertEquals(List.of("act", "cat", "tac"), group);
    }

    @Test
    void testFindAnagrams_withNoAnagrams() {
        List<String> input = Arrays.asList("apple", "banana", "carrot");

        Map<String, List<String>> result = anagramService.findAnagrams(input);

        assertEquals(3, result.size());
        for (List<String> group : result.values()) {
            assertEquals(1, group.size());
        }
    }

    @Test
    void testFindAnagrams_withEmptyList() {
        List<String> input = Collections.emptyList();

        Map<String, List<String>> result = anagramService.findAnagrams(input);

        assertTrue(result.isEmpty());
    }
}
