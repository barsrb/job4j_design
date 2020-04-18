package ru.job4j.job4j.junior.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        List<String> searchResult = search(start, "java");
        searchResult.forEach(System.out::println);
    }

    public static List<String> search(Path root, String ext) throws IOException {
        SearchFiles searchFiles = new SearchFiles(ext);
        Files.walkFileTree(root, searchFiles);
        return searchFiles.getFounded();
    }
}
