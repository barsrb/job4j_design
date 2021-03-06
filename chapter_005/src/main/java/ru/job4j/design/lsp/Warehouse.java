package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Warehouse implements Store, StoreExporter {
    private final List<Food> foods = new ArrayList<>();
    private final Predicate<Food> filter;

    public Warehouse(Predicate<Food> filter) {
        this.filter = filter;
    }

    @Override
    public String exportStore() {
        StringBuilder result = new StringBuilder();
        result.append("-- In warehouse: --").append(System.lineSeparator());
        for (Food food : getFood()) {
                result.append(String.format("Name: %s - %.2f$. Expectation: %d%%%n", food.getName(), food.getPrice(), food.getExpirationPercent()));
                result.append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public boolean accept(Food food) {
        return filter.test(food);
    }

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public List<Food> getFood() {
        return foods;
    }

    @Override
    public void clear() {
        foods.clear();
    }
}
