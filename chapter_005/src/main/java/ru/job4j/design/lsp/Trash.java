package ru.job4j.design.lsp;

public class Trash extends Store {
    @Override
    void showFoods() {
        System.out.println("-- In trash: --");
        for (Food food : getFood()) {
            System.out.printf("Name: %s%n", food.getName());
        }
        System.out.println();
    }

    @Override
    public void move(Food food) {
        food.setPrice(0.0);
        super.move(food);
    }
}
