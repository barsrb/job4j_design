package ru.job4j.design.lsp;

import java.util.List;

public interface Store {
    boolean accept(Food food);
    void add(Food food);
    List<Food> getFood();
}
