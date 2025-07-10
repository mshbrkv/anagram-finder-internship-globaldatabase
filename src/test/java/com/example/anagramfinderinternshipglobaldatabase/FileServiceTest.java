package com.example.anagramfinderinternshipglobaldatabase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void testReadFile_returnsCorrectContent() throws IOException {

        Path tempFile = tempDir.resolve("testfile.txt");
        List<String> expectedLines = List.of("hello", "world", "anagram", "test");
        Files.write(tempFile, expectedLines);


        List<String> result = FileService.readFile(tempFile.toString());


        assertEquals(expectedLines, result);
    }

    @Test
    void testReadFile_throwsExceptionIfFileNotFound() {

        String nonExistentPath = tempDir.resolve("nonexistent.txt").toString();


        assertThrows(IOException.class, () -> FileService.readFile(nonExistentPath));
    }
}
