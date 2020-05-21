package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Shop implements Store, StoreExporter {
    private final List<Food> foods = new ArrayList<>();
    private final Predicate<Food> filter;

    public Shop(Predicate<Food> filter) {
        this.filter = filter;
    }

    @Override
    public boolean accept(Food food) {
        return filter.test(food);
    }

    @Override
    public void add(Food food) {
        if (food.getExpirationPercent() < 25) {
            food.setDiscount(25 - food.getExpirationPercent());
        }
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

    @Override
    public String exportStore() {
        StringBuilder result = new StringBuilder();
        result.append("-- In store: --").append(System.lineSeparator());
        for (Food food : getFood()) {
            if (food.getDiscount() > 0) {
                result.append(String.format("Name: %s - %1.2f$. Discount: %d%%%n", food.getName(), food.getPrice() * (100 - food.getDiscount()) / 100, food.getDiscount()));
            } else {
                result.append(String.format("Name: %s - %1.2f$%n", food.getName(), food.getPrice()));
            }
            result.append(System.lineSeparator());
        }
        return result.toString();
    }
}
