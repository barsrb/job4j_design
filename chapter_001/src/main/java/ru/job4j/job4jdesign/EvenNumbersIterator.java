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
        if (index < 0 || array.length <= index) {
            return false;
        }
        index = getNextEven();
        return index >= 0;
    }

    private int getNextEven() {
        for (int pos = index; pos < array.length; pos++) {
            if (array[pos] % 2 == 0) {
                return pos;
            }
        }
        return -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }
}
