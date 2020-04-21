package ru.job4j.job4j.junior.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileLister {
    private static final Logger LOG = LoggerFactory.getLogger(FileLister.class.getName());

    public static void main(String[] args) {
        SearchParameters parameters;
        try {
            parameters = new SearchParameters(args);
        } catch (IllegalStateException e) {
            LOG.error("Exception in parameters: ", e);
            return;
        }
        List<Path> files;
        try {
            files = search(parameters.getDirectory(), parameters.getPattern());
        } catch (IOException e) {
            LOG.error("Exception in searching files: ", e);
            return;
        }

        saveResult(files, parameters.getLogFile());

    }

    private static void saveResult(List<Path> files, String logFile) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(logFile)
                ))) {
            for (Path file : files) {
                out.write(file.toFile().getName());
                out.write(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Path> search(String path, String pattern) throws IOException {
        FilteredVisitor filteredVisitor = new FilteredVisitor(pattern);
        Files.walkFileTree(Path.of(path), filteredVisitor);
        return filteredVisitor.getFounded();
    }
}
