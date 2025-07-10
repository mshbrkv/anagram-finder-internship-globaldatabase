# Design Decisions

## Overview

This project aims to efficiently find and group anagrams from a list of words read from a file, using Java and Spring
Boot as the framework.

---

## Approach

### 1. Use of Java Streams and Collectors

- **Reasoning:**  
  Java Streams API provides a concise and expressive way to process collections. Using `Collectors.groupingBy` allows
  grouping words by their sorted character representation efficiently and readably.

- **Maintainability:**  
  The code is clean and easy to understand or extend. Adding new transformations or filters is straightforward.

- **Performance:**  
  Stream operations are optimized internally and avoid manual loops, improving code performance and readability.

---

### 2. Sorting Characters to Find Anagrams

- **Reasoning:**  
  Sorting the characters of each word creates a normalized key for grouping anagrams (e.g., "cat" and "act" both map
  to "act").

- **Performance:**  
  Sorting small strings is fast (O(n log n) per word), suitable for typical word sizes.

- **Scalability:**  
  This approach scales well with input size and is easy to parallelize if needed.

---

### 3. Using `TreeMap` and `TreeSet` for Sorting

- **Reasoning:**  
  `TreeMap` is used to keep the grouped anagrams sorted by their keys alphabetically.  
  `TreeSet` is used inside the collector to ensure the lists of anagrams are sorted and contain no duplicates.

- **Maintainability:**  
  Built-in Java collections reduce the need for custom sorting code, keeping the implementation simple.

- **Performance:**  
  `TreeMap` and `TreeSet` provide log(n) time complexity for insertions and retrievals, efficient for moderate dataset
  sizes.

---

### 4. File Reading Using `java.nio.file.Files`

- **Reasoning:**  
  Using `Files.readAllLines(Path)` is straightforward and part of the standard Java library, avoiding dependencies on
  third-party libraries.

- **Maintainability:**  
  Simple and reliable file reading method with good exception handling.

- **Performance:**  
  Suitable for files that fit in memory. For huge files, a streaming approach could be considered.

---

### 5. Spring Boot Integration

- **Reasoning:**  
  Spring Boot is used to bootstrap the application, allowing easy future extension to expose REST APIs or integrate with
  other Spring components.

- **Scalability:**  
  The project can be extended from a command-line tool to a web service without major redesign.

- **Maintainability:**  
  Spring Boot’s conventions and auto-configuration reduce boilerplate and ease future development.

---

# Scalability Considerations

## How would the current solution handle 10 million words?

The current implementation reads all words into memory and uses Java Streams with `groupingBy` and `TreeMap`. This
approach can handle around 10 million words on modern hardware if:

- Sufficient heap space is allocated (`-Xmx` option).
- The words are not excessively long.
- The number of unique anagram groups remains manageable.

**Potential bottlenecks:**

- Memory usage: all words are held in memory.
- CPU: sorting each word (O(n log n)) and grouping them.

**Improvements for this scale:**

- Use parallel streams to utilize multiple CPU cores.
- Switch to more memory-efficient data structures.
- Profile memory usage and optimize character handling (e.g., using char arrays instead of Strings where possible).

---

## What changes would be needed for 100 billion words?

Handling 100 billion words requires a fundamental redesign — the in-memory approach is no longer viable.

**Proposed changes:**

1. **Batch/Chunk Processing:**

- Read and process words in chunks (e.g., 1 million words at a time).
- Write intermediate results (e.g., anagram group mappings) to disk or a distributed file system.

2. **External Storage:**

- Use a key-value store (e.g., Redis) grouped results.
- Each key = sorted word, value = list of original words.

3. **MapReduce/Distributed Processing:**

- Use Apache Hadoop, Spark, or similar frameworks to distribute processing across multiple nodes.
- Map: emit (sorted_word, original_word)
- Reduce: group original words by sorted_word.

4. **Streaming Processing (Optional):**

- Use Apache Kafka and stream processing (e.g., Kafka Streams) for real-time ingestion and grouping.


