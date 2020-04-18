package ru.job4j.job4j.junior.io;

import java.io.File;

public class Dir {
    public static void getFiles(String directoryPath) {
        File file = new File(directoryPath);
        if (!file.exists()) {
            throw new IllegalStateException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalStateException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subFile : file.listFiles()) {
            System.out.println(subFile.getName() + ". Size - " + subFile.length());
        }
    }

    public static void main(String[] args) {
        getFiles("./");
    }
}
