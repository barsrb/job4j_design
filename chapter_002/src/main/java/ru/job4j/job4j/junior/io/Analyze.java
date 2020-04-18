package ru.job4j.job4j.junior.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Analyze {

        public static void unavailable(String source, String target) {
            boolean available = true;
            String unavailableStartTime = null;
            List<String> unavailableList = new ArrayList<>();

            try (BufferedReader in = new BufferedReader(new FileReader(source))) {
                List<String> lines = new ArrayList<>();
                in.lines().forEach(lines::add);
                for (String line : lines) {
                    String[] log = line.split(" ");
                    if (log[0].equals("200")) {
                        if (!available) {
                            available = true;
                            unavailableList.add(unavailableStartTime + ";" + log[1]);
                        }
                    } else {
                        if (available) {
                            available = false;
                            unavailableStartTime = log[1];
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            writeLog(unavailableList, target);
        }

        private static void writeLog(List<String> lines, String filename) {
            try (PrintWriter out = new PrintWriter(new FileOutputStream(filename))) {
                out.print(getString(lines));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static String getString(List<String> lines) {
            StringJoiner out = new StringJoiner(System.lineSeparator());
            for (String line : lines) {
                out.add(line);
            }
            return out.toString();
        }

}
