package ru.job4j.job4jdesign.list;

import java.util.Arrays;
import java.util.Iterator;

public class DynamicArray<E> implements Iterable<E> {

    private Object[] elements;

    private int index = 0;
    private int modCount = 0;

    public DynamicArray() {
        elements = new Object[5];
    }

    public void add(E value) {
        if (elements.length == index) {
            increaseArray();
        }
        elements[index++] = value;
        modCount++;
    }

    private void increaseArray() {
        int newArraySize = elements.length * 2;
        elements = Arrays.copyOf(elements, newArraySize);
    }

    public E get(int index) {
        return (E) elements[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new DynamicArrayIterator<>(this);
    }

    public int getModCount() {
        return modCount;
    }

    public int getSize() {
        return index;
    }
}
