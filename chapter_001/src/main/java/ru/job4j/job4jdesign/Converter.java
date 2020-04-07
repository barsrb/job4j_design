package ru.job4j.job4jdesign;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private Iterator<Integer> inner = it.next();
            private Integer element = null;

            @Override
            public boolean hasNext() {
                if (element != null) {
                    return true;
                }

                while (it.hasNext() || inner.hasNext()) {
                    if (!inner.hasNext()) {
                        inner = it.next();
                    }
                    if (inner.hasNext()) {
                        element = inner.next();
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    Integer result = element;
                    element = null;
                    return result;
                }
            }
        };

    }
}