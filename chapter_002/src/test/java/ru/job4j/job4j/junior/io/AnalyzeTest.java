package ru.job4j.job4j.junior.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class AnalyzeTest {
    @Test
    public void testParsedLog() {
        String serverStatusFile = "../files/serverStatus.csv";
        String serverUnavailableFile = "../files/serverUnavailable.csv";
        Analyze.unavailable(serverStatusFile, serverUnavailableFile);

        List<String> expected = List.of("10:58:01;11:00:01", "11:01:02;11:02:02");
        List<String> result = readLog(serverUnavailableFile);

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