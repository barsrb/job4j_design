package ru.job4j.job4jdesign.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private SimpleArray<T> array;
    private int index;

    public SimpleArrayIterator(SimpleArray<T> ts) {
        array = ts;
    }

    @Override
    public boolean hasNext() {
        return index < array.length();
    }

    @Override
    public T next() {
        if (hasNext()) {
            return array.get(index++);
        } else {
            throw new NoSuchElementException();
        }
    }
}
