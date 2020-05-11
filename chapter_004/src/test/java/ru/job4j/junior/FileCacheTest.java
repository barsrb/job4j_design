package ru.job4j.junior;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class FileCacheTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testCache() throws IOException {
        File file1 = folder.newFile("file1.txt");
        File file2 = folder.newFile("file2.txt");
        File file3 = folder.newFile("file3.txt");
        try (PrintWriter pw1 = new PrintWriter(file1);
             PrintWriter pw2 = new PrintWriter(file2);
             PrintWriter pw3 = new PrintWriter(file3)) {
            pw1.print("file1" + System.lineSeparator() + "file1");
            pw2.print("file2" + System.lineSeparator() + "file2");
            pw3.print("file3" + System.lineSeparator() + "file3");
        }
        FileCache fileCache = new FileCache(folder.getRoot().toString());
        assertThat(fileCache.get("file1.txt"), is("file1" + System.lineSeparator() + "file1"));
    }

    @Test
    public void testWithGC() throws IOException {
        File file1 = folder.newFile("file1.txt");
        File file2 = folder.newFile("file2.txt");
        File file3 = folder.newFile("file3.txt");
        try (PrintWriter pw1 = new PrintWriter(file1);
             PrintWriter pw2 = new PrintWriter(file2);
             PrintWriter pw3 = new PrintWriter(file3)) {
            pw1.print("file1" + System.lineSeparator() + "file1");
            pw2.print("file2" + System.lineSeparator() + "file2");
            pw3.print("file3" + System.lineSeparator() + "file3");
        }
        FileCache fileCache = new FileCache(folder.getRoot().toString());
        fileCache.get("file1.txt");
        fileCache.get("file2.txt");
        fileCache.get("file3.txt");

        GCTest.info("Before GC");
        System.gc();
        GCTest.info("After GC");

        Cache<String> c1 = new Cache<>();
        String s = "Test string 1";
        c1.put(s);

        Cache<String> c2 = new Cache<>();
        s = "Test string 2";
        c2.put(s);

        Cache<String> c3 = new Cache<>();
        s = "Test string 3";
        c3.put(s);

        System.out.println(c1.get());
        System.out.println(c2.get());
        System.out.println(c3.get());
        String[] x = new String[1000000];
        GCTest.info("Before second GC");
        System.gc();
        GCTest.info("After second GC");
        System.out.println(c1.get());
        System.out.println(c2.get());
        System.out.println(c3.get());
    }
}