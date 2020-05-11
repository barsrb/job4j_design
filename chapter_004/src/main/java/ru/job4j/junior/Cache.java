package ru.job4j.junior;

import java.lang.ref.SoftReference;

public class Cache<T> {
    SoftReference<T> reference;
    public T get() {
        return  reference.get();
    }
    public void put(T t) {
        reference = new SoftReference<>(t);
    }

}
