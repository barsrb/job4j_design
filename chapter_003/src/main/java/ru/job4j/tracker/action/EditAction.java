package ru.job4j.tracker.action;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.store.Store;

public class EditAction implements UserAction {
    @Override
    public String name() {
        return "=== Edit Item ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        String editedID = input.askStr("Enter ID of edited Item: ");
        String name = input.askStr("Enter new name: ");
        Item item = new Item(name);
        if (tracker.replace(editedID, item)) {
            System.out.println("Item edit successful.");
        } else {
            System.out.println("Item edit failed.");
        }
        return true;
    }
}
