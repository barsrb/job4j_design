package ru.job4j.design.lsp;

public class Warehouse extends Store {
    @Override
    void showFoods() {
        System.out.println("-- In warehouse: --");
        for (Food food : getFood()) {
                System.out.printf("Name: %s - %.2f$. Expectation: %d%%%n", food.getName(), food.getPrice(), food.getExpirationPercent());
        }
        System.out.println();
    }
}
