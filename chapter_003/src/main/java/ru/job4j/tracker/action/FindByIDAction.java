package ru.job4j.tracker.action;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.store.Store;

public class FindByIDAction implements UserAction {
    @Override
    public String name() {
        return "=== Find Item by ID ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String itemID = input.askStr("Enter ID: ");
        Item item = tracker.findById(itemID);
        if (item != null) {
            System.out.println("Item found. Name - " + item.getName());
        } else {
            System.out.println("Item was not found.");
        }
        return true;
    }
}
