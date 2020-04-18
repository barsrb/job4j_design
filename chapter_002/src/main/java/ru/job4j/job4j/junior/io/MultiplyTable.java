package ru.job4j.job4j.junior.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class MultiplyTable {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int size = 5;
            int[][] table = multiple(size);
            for (int[] row : table) {
                out.write(Arrays.toString(row).getBytes());
                out.write(System.lineSeparator().getBytes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int row = 1; row <= size; row++) {
            for (int col = 1; col <= size; col++) {
                table[row - 1][col - 1] = row * col;
            }
        }
        return table;
    }
}
