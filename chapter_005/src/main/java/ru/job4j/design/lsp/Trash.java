package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Trash implements Store, StoreExporter {
    private final List<Food> foods = new ArrayList<>();
    private final Predicate<Food> filter;

    public Trash(Predicate<Food> filter) {
        this.filter = filter;
    }

    @Override
    public boolean accept(Food food) {
        return filter.test(food);
    }

    @Override
    public void add(Food food) {
        food.setPrice(0.0);
        foods.add(food);
    }

    @Override
    public List<Food> getFood() {
        return foods;
    }

    @Override
    public String exportStore() {
        StringBuilder result = new StringBuilder();
        result.append("-- In trash: --").append(System.lineSeparator());
        for (Food food : getFood()) {
            result.append(String.format("Name: %s%n", food.getName()));
            result.append(System.lineSeparator());
        }
        return result.toString();
    }
}
