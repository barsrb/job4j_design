package ru.job4j.job4j.junior.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            List<String> lines = new ArrayList<>();
            in.lines().forEach(lines::add);
            for (String line : lines) {
                parseLine(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseLine(String line) {
        line = line.trim();
        if (line.startsWith("#") || line.length() == 0) {
            return;
        }
        int eqPos = line.indexOf("=");
        if (eqPos < 1) {
            return;
        }
        String key = line.substring(0, eqPos);
        String value = line.substring(eqPos + 1);
        values.put(key, value);
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
