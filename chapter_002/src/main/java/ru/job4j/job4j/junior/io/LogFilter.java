package ru.job4j.job4j.junior.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> filtered = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            in.lines().forEach(lines::add);
            for (String line : lines) {
                String[] splited = line.split(" ");
                if (splited[splited.length - 2].equals("404")) {
                    filtered.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filtered;
    }

    public static void main(String[] args) {
        List<String> log = filter("files/log.txt");
        System.out.println(log);
    }
}
