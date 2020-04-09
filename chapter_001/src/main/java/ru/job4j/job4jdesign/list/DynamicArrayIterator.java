package ru.job4j.job4jdesign.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArrayIterator<E> implements Iterator<E> {
    private DynamicArray<E> elements;
    private final int expectedModCount;
    private int index;

    public DynamicArrayIterator(DynamicArray<E> array) {
        elements = array;
        expectedModCount = array.getModCount();
    }

    @Override
    public boolean hasNext() {
        if (expectedModCount != elements.getModCount()) {
            throw new ConcurrentModificationException();
        }
        return index < elements.getSize();
    }

    @Override
    public E next() {
        if (expectedModCount != elements.getModCount()) {
            throw new ConcurrentModificationException();
        }
        if (hasNext()) {
            throw new NoSuchElementException();
        }
        return elements.get(index++);
    }
}
