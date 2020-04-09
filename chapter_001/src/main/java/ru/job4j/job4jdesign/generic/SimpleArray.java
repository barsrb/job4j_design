package ru.job4j.job4jdesign.generic;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] elements;
    private int index = 0;

    public SimpleArray(int size) {
        elements = new Object[size];
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>(this);
    }

    public void add(T model) {
        if (index == elements.length) {
            throw new ArrayStoreException();
        }
        elements[index++] = model;
    }

    public void set(int position, T model) {
        if (position >= elements.length) {
            throw new IndexOutOfBoundsException();
        }

        checkForPositionInBounds(position);
        elements[position] = model;
    }

    public void remove(int position) {
        checkForPositionInBounds(position);
        System.arraycopy(elements, position + 1, elements, position, index - position);
        elements[--index] = null;
    }

    public T get(int position) {
        if (position >= elements.length) {
            throw new IndexOutOfBoundsException();
        }

        checkForPositionInBounds(position);
        return (T) elements[position];
    }

    private void checkForPositionInBounds(int position) {
        if (position >= this.index) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int length() {
        return index;
    }
}