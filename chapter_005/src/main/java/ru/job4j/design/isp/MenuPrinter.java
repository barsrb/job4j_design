package ru.job4j.design.isp;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MenuPrinter {
    private static void printMenu(MenuItem root, int level) {
        String delimiter = IntStream.range(0, level + 1).mapToObj(i -> "-").collect(Collectors.joining(""));
        System.out.println(delimiter + " " + root.getName());
        for (MenuItem subItem : root.getSubItems()) {
            printMenu(subItem, level + 1);
        }
    }

    public static void printMenu(MenuItem root) {
        printMenu(root, 0);
    }
}
