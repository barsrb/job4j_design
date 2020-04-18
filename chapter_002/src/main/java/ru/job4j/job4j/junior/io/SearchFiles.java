package ru.job4j.job4j.junior.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles implements FileVisitor<Path> {

    private List<Path> founded = new ArrayList<>();
    private String include;
    private String exclude;

    public SearchFiles include(String include) {
        this.include = include != null ? include.replace("*", "") : null;
        return this;
    }

    public SearchFiles exclude(String exclude) {
        this.exclude = exclude != null ? exclude.replace("*", "") : null;
        return this;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (fileCorrect(file)) {
            founded.add(file);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }

    public List<Path> getFounded() {
        return founded;
    }

    private boolean fileCorrect(Path file) {
        if (include != null && !file.toString().endsWith(include)) {
            return false;
        }
        if (exclude != null && file.toString().endsWith(exclude)) {
            return false;
        }
        return true;
    }

}