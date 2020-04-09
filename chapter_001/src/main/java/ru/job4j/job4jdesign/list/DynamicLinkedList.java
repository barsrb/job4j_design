package ru.job4j.job4jdesign.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicLinkedList<E> implements Iterable<E> {

    private Node<E> first = new Node<>();
    private int modCount = 0;
    private int size = 0;

    public void add(E value) {
        Node<E> pointer = first;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        pointer.data = value;
        pointer.next = new Node<>();
        modCount++;
        size++;
    }

    public E get(int index) {
        Node<E> node = getNode(index);
        return node.data;
    }

    private Node<E> getNode(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> pointer = first;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer;
    }

    @Override
    public Iterator<E> iterator() {
        return new DynamicLinkedListIterator<>(this);
    }

    public int getModCount() {
        return modCount;
    }

    public int getSize() {
        return size;
    }


    public static class Node<E> {
        public E data;
        public Node<E> next;
    }

    public void deleteLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size == 1) {
            first = new Node<>();
        } else {
            getNode(size - 2).next = null;
        }
        size--;
    }

}
