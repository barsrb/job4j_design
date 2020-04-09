package ru.job4j.job4jdesign.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicLinkedListIterator<E> implements Iterator<E> {
    private DynamicLinkedList<E> elements;
    private final int expectedModCount;
    private int index = 0;


    public DynamicLinkedListIterator(DynamicLinkedList<E> list) {
        this.elements = list;
        this.expectedModCount = list.getModCount();
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
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return elements.get(index++);
    }
}
