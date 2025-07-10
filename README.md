# Anagram Finder

A simple Java project to find and group anagrams from a file using Spring Boot.

---

## Description

The program reads words from a text file, groups anagrams together, and prints them to the console. It also runs as a
Spring Boot application.

---

## Requirements

- Java 17

- Maven

- An IDE (e.g., IntelliJ IDEA, Eclipse) or command line

---

## Installation and Running

1. Clone the repository:

       git clone https://github.com/mshbrkv/anagram-finder-internship-globaldatabase

2. Place the file containing words for anagram search at the path used in the code Or update the file path in the main
   method of the AnagramFinderInternshipGlobaldatabaseApplication class to match your system.

3. Build the project

         mvn clean package

4. Run the application

5. The output — groups of anagrams sorted alphabetically — will be printed to the console.

---

## Project Structure

- `AnagramService` — service for finding and grouping anagrams.

- `FileService` — utility to read the file with words.

- `AnagramFinderInternshipGlobaldatabaseApplication` — main class with the main method to run the application.