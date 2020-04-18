package ru.job4j.job4j.junior.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalStateException("Root folder is null. Usage java -jar Search.jar SEARCH_FOLDER SEARCH_EXCEPTION");
        }
        Path start = Paths.get(args[0]);
        String ext = args[1];
        List<String> searchResult = search(start, ext);
        searchResult.forEach(System.out::println);
    }

    public static List<String> search(Path root, String ext) throws IOException {
        SearchFiles searchFiles = new SearchFiles(ext);
        Files.walkFileTree(root, searchFiles);
        return searchFiles.getFounded();
    }
}
