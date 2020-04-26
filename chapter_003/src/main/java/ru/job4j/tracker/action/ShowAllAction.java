package ru.job4j.tracker.action;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.store.Store;

import java.util.List;

public class ShowAllAction implements UserAction {
    @Override
    public String name() {
        return "=== Show all items ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        List<Item> items = tracker.findAll();
        System.out.println("Items in tracker - " + items.size());
        for (Item item : items) {
            System.out.println("Item ID - " + item.getId() + ", name - " + item.getName());
        }
        return true;
    }
}
