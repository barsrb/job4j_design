package ru.job4j.job4jdesign.list;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private DynamicArray<E> list = new DynamicArray<>();

    public boolean add(E value) {
        if (list.contains(value)) {
            return false;
        } else {
            list.add(value);
            return true;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
