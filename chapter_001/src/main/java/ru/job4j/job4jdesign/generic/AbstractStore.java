package ru.job4j.job4jdesign.generic;

import java.util.NoSuchElementException;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    protected SimpleArray<T> storage;

    public AbstractStore(int size) {
        this.storage = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        storage.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int pos = findPositionById(id);
        if (pos > -1) {
            storage.set(pos, model);
            return true;
        }
        return false;
    }

    private int findPositionById(String id) {
        for (int index = 0; index < storage.length(); index++) {
            if (storage.get(index).getId().equals(id)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public boolean delete(String id) {
        int pos = findPositionById(id);
        if (pos > -1) {
            storage.remove(pos);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        int pos = findPositionById(id);
        if (pos < 0) {
            throw new NoSuchElementException();
        }
        return storage.get(pos);
    }
}
