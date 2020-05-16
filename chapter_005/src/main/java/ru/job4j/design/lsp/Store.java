package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public abstract class Store {
    private final List<Food> foods = new ArrayList<>();

    public void move(Food food) {
        foods.add(food);
    }

    public List<Food> getFood() {
        return foods;
    }

    abstract void showFoods();
}
