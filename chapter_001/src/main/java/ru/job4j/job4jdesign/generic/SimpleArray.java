package ru.job4j.job4jdesign.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

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

        if (position > index + 1) {
            throw new NoSuchElementException();
        }
        elements[position] = model;
    }

    public void remove(int position) {
        if (position >= index) {
            throw new NoSuchElementException();
        }
        System.arraycopy(elements, position + 1, elements, position, index - position);
        elements[--index] = null;
    }

    public T get(int position) {
        if (position >= elements.length) {
            throw new IndexOutOfBoundsException();
        }

        if (position > index) {
            throw new NoSuchElementException();
        }
        return (T) elements[position];
    }

    public int length() {
        return index;
    }
}