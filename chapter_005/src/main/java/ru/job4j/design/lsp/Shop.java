package ru.job4j.design.lsp;

public class Shop extends Store {
    @Override
    void showFoods() {
        System.out.println("-- In store: --");
        for (Food food : getFood()) {
            if (food.getDiscount() > 0) {
                System.out.printf("Name: %s - %1.2f$. Discount: %d%%%n", food.getName(), food.getPrice() * (100 - food.getDiscount()) / 100, food.getDiscount());
            } else {
                System.out.printf("Name: %s - %1.2f$%n", food.getName(), food.getPrice());
            }
        }
        System.out.println();
    }

    @Override
    public void move(Food food) {
        food.setDiscount(25 - food.getExpirationPercent());
        super.move(food);
    }
}
