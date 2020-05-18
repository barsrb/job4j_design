package ru.job4j.design.isp;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private final List<MenuItem> subItems = new ArrayList<>();
    private final String name;
    private Action action;

    public MenuItem(String name) {
        this.name = name;
    }

    public List<MenuItem> getSubItems() {
        return subItems;
    }

    public void addItem(MenuItem menuItem) {
        subItems.add(menuItem);
    }

    public String getName() {
        return name;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void doAction() {
        if (action == null) {
            throw new UnsupportedOperationException();
        }
        action.doAction();
    }
}
