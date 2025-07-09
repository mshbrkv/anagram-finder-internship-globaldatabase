package com.example.anagramfinderinternshipglobaldatabase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileService {
    public static List<String> readFile(String filePath) throws IOException {

        return Files.readAllLines(Path.of(filePath));
    }

}
