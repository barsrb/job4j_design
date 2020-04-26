package ru.job4j.tracker.store;

import ru.job4j.tracker.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemStore implements Store {
    private List<Item> items;

    public MemStore() {
        this.init();
    }

    @Override
    public void init() {
        items = new ArrayList<>();
    }

    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    public List<Item> findAll() {
        return new ArrayList<>(items);
    }

    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    public Item findById(String id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        if (index > -1) {
            Item current = items.get(index);
            item.setId(current.getId());
            items.set(index, item);
            return true;
        }
        return false;
    }

    public boolean delete(String id) {
        boolean deleted = false;
        int index = indexOf(id);
        if (index > -1) {
            items.remove(index);
            deleted = true;
        }
        return deleted;
    }

    private int indexOf(String id) {
        int rsl = -1;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                rsl = items.indexOf(item);
                break;
            }
        }
        return rsl;
    }

    @Override
    public void close() {
        items = null;
    }
}
