package ru.job4j.junior;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileCache {
    private final String path;
    private final Map<String, Cache<String>> cacheMap = new HashMap<>();

    public FileCache(String path) {
        this.path = path;
    }

    public String get(String filename) {
        if (!cacheMap.containsKey(filename)) {
            Cache<String> cache = new Cache<>();
            cache.put(loadFile(filename));
            cacheMap.put(filename, cache);
        } else if (cacheMap.get(filename).get() == null) {
            cacheMap.get(filename).put(loadFile(filename));
        }

        return cacheMap.get(filename).get();
    }

    private String loadFile(String filename) {
        List<String> lines = new ArrayList<>();
        String result = null;
        try (BufferedReader in = new BufferedReader(new FileReader(path + "/" + filename))) {
            in.lines().forEach(lines::add);
            result = String.join(System.lineSeparator(), lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
