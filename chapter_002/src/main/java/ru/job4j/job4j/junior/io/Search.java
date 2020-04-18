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
        List<Path> searchResult = search(start, ext);
        searchResult.forEach(file -> System.out.println(file.toFile().getName()));
    }

    public static List<Path> search(Path root, String include) throws IOException {
        return search(root, include, null);
    }

    public static List<Path> search(Path root, String include, String exclude) throws IOException {
        SearchFiles searchFiles = new SearchFiles().include(include).exclude(exclude);
        Files.walkFileTree(root, searchFiles);
        return searchFiles.getFounded();
    }
}
