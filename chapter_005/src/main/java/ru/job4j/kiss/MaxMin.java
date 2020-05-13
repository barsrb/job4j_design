package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public static <T> T max(List<T> value, Comparator<T> comparator) {
        return compare(value, comparator, true);
    }

    public static <T> T min(List<T> value, Comparator<T> comparator) {
        return compare(value, comparator, false);
    }

    private static <T> T compare(List<T> value, Comparator<T> comparator, boolean max) {
        T result = value.get(0);

        for (T comp : value) {
            if (max) {
                result = comparator.compare(comp, result) > 0 ? comp : result;
            } else {
                result = comparator.compare(comp, result) < 0 ? comp : result;
            }
        }

        return result;
    }
}