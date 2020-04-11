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

        int next = -1;

        for (int pos = index; pos < array.length; pos++) {
            if (array[pos] % 2 == 0) {
                next = pos;
                break;
            }
        }
        index = next;
        return index >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }
}
