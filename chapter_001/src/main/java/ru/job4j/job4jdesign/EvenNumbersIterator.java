package ru.job4j.job4jdesign;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private int[] array;
    private int index = 0;

    public EvenNumbersIterator(int[] ints) {
        array = ints;
    }

    @Override
    public boolean hasNext() {
        return getNextEven() >= 0;
    }

    private int getNextEven() {
        if (array.length <= index) {
            return -1;
        }
        for (int pos = index; pos < array.length; pos++) {
            if (array[pos] % 2 == 0) {
                return pos;
            }
        }
        return -1;
    }

    @Override
    public Integer next() {
        int pos = getNextEven();
        if (pos < 0) {
            throw new NoSuchElementException();
        }
        index = pos + 1;
        return array[pos];
    }
}
