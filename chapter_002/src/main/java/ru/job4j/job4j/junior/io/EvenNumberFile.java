package ru.job4j.job4j.junior.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream in = new FileInputStream("files/even.txt")) {

            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] lines = text.toString().split(System.lineSeparator());
        for (String line : lines) {
            System.out.println("Number " + line + " even: " + isNumberEven(line));
        }

    }

    private static boolean isNumberEven(String line) {
        return Integer.parseInt(line) % 2 == 0;
    }
}
