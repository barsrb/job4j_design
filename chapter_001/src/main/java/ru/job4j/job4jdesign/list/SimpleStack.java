package ru.job4j.job4jdesign.list;

public class SimpleStack<E> {
    private DynamicLinkedList<E> linked = new DynamicLinkedList<>();

    public E poll() {
        E val = linked.get(linked.getSize() - 1);
        linked.deleteLast();
        return val;
    }

    public void push(E value) {
        linked.add(value);
    }

    public int size() {
        return linked.getSize();
    }
}
