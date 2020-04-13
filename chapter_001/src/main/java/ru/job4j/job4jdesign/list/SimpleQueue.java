package ru.job4j.job4jdesign.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (out.size() == 0) {
            switchStacks(in, out);
        }
        if (out.size() == 0) {
            throw new NoSuchElementException();
        }
        return out.poll();
    }

    public void push(T value) {
        if (in.size() == 0) {
            switchStacks(out, in);
        }
        in.push(value);
    }

    private void switchStacks(SimpleStack<T> from, SimpleStack<T> to) {
        for (int i = 0; i < in.size() + out.size(); i++) {
            to.push(from.poll());
        }
    }
}
