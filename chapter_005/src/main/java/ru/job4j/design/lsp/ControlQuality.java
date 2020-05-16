package ru.job4j.design.lsp;

import java.util.List;

public class ControlQuality {
    private final Warehouse warehouse = new Warehouse();
    private final Shop shop = new Shop();
    private final Trash trash = new Trash();

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Shop getShop() {
        return shop;
    }

    public Trash getTrash() {
        return trash;
    }

    public void checkFood(List<Food> foods) {
        for (Food food : foods) {
            if (food.getExpirationPercent() >= 75) {
                food.moveFood(warehouse);
            }  else if (food.getExpirationPercent() > 0) {
                food.moveFood(shop);
            } else {
                food.moveFood(trash);
            }
        }
    }

    public void showStore(Store store) {
        store.showFoods();
    }
}
