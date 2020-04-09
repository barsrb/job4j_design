package ru.job4j.job4jdesign.list;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private DynamicArray<E> list = new DynamicArray<>();

    public boolean add(E value) {
        boolean contains = false;
        for (E item : list) {
            if (item.equals(value)) {
                contains = true;
                break;
            }
        }
        if (contains) {
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
