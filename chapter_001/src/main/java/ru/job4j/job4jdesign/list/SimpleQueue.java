package ru.job4j.job4jdesign.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private SimpleStack<T> stack = new SimpleStack<>();
    private int size = 0;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        SimpleStack<T> tempStack = new SimpleStack<>();
        T result;
        for (int i = 0; i < size - 1; i++) {
            tempStack.push(stack.poll());
        }
        result = stack.poll();
        for (int i = 0; i < size - 1; i++) {
            stack.push(tempStack.poll());
        }
        size--;
        return result;
    }

    public void push(T value) {
        stack.push(value);
        size++;
    }
}
