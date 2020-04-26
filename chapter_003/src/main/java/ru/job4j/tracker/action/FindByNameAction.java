package ru.job4j.tracker.action;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.store.Store;

import java.util.List;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find Items by name ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String itemName = input.askStr("Enter name: ");
        List<Item> items = tracker.findByName(itemName);
        System.out.println("Founded items - " + items.size());
        for (Item item : items) {
            System.out.println("Item ID - " + item.getId() + ", name - " + item.getName());
        }
        return true;
    }
}
