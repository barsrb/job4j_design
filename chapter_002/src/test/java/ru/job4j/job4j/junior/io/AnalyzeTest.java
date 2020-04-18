package ru.job4j.job4j.junior.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class AnalyzeTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testParsedLog() throws IOException {
        File source = folder.newFile("serverStatus.csv");
        File target = folder.newFile("serverUnavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01" + System.lineSeparator()
                    + "200 10:57:01" + System.lineSeparator()
                    + "400 10:58:01" + System.lineSeparator()
                    + "500 10:59:01" + System.lineSeparator()
                    + "200 11:00:01" + System.lineSeparator()
                    + "500 11:01:02" + System.lineSeparator()
                    + "200 11:02:02");
        }

        Analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        List<String> expected = List.of("10:58:01;11:00:01", "11:01:02;11:02:02");
        List<String> result = readLog(target.getAbsolutePath());

        assertThat(result, is(expected));
    }

    private List<String> readLog(String serverUnavailableFile) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(serverUnavailableFile))) {
            in.lines().forEach(result::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}