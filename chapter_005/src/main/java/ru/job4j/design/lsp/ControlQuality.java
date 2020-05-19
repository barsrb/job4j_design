package ru.job4j.design.lsp;

import java.util.List;

public class ControlQuality {


    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }


    public void checkFood(List<Food> foods) {
        for (Food food : foods) {
            for (Store store : stores) {
                if (store.accept(food)) {
                    store.add(food);
                    break;
                }
            }
        }
    }

    public List<Store> getStores() {
        return stores;
    }
}
