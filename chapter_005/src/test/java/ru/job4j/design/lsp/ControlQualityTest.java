package ru.job4j.design.lsp;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class ControlQualityTest {

    List<Food> foods;
    Milk milk1, milk2, milk3, milk4;
    Meat meat1, meat2;

    Warehouse warehouse = new Warehouse(food -> food.getExpirationPercent() > 75);
    Shop shop = new Shop(food -> food.getExpirationPercent() <= 75 && food.getExpirationPercent() > 0);
    Trash trash = new Trash(food -> food.getExpirationPercent() <= 0);
    List<Store> stores = List.of(warehouse, shop, trash);

    @Before
    public void initList() {
        foods = new ArrayList<>();

        Calendar create = Calendar.getInstance();
        create.add(Calendar.DAY_OF_MONTH, -27);
        milk1 = new Milk("Milk1", create, 250.0);
        foods.add(milk1);

        create = Calendar.getInstance();
        create.add(Calendar.DAY_OF_MONTH, -15);
        milk2 = new Milk("Milk2", create, 250.0);
        foods.add(milk2);

        create = Calendar.getInstance();
        create.add(Calendar.DAY_OF_MONTH, -3);
        milk3 = new Milk("Milk3", create, 150.8);
        foods.add(milk3);

        create = Calendar.getInstance();
        create.add(Calendar.DAY_OF_MONTH, -35);
        milk4 = new Milk("Milk4", create, 100.0);
        foods.add(milk4);

        create = Calendar.getInstance();
        Calendar expired = Calendar.getInstance();
        create.add(Calendar.DAY_OF_MONTH, -10);
        expired.add(Calendar.DAY_OF_MONTH, +90);
        meat1 = new Meat("Meat1", create, expired, 100.0);
        foods.add(meat1);

        create = Calendar.getInstance();
        expired = Calendar.getInstance();
        create.add(Calendar.DAY_OF_MONTH, -90);
        expired.add(Calendar.DAY_OF_MONTH, +10);
        meat2 = new Meat("Meat1", create, expired, 100.0);
        foods.add(meat2);
    }

    @Test
    public void storesInControllerSameIsInTestedList() {
        ControlQuality controlQuality = new ControlQuality(stores);
        assertThat(controlQuality.getStores(), is(stores));
    }

    @Test
    public void thereTwoInWarehouse() {
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.checkFood(foods);
        List<Food> result = warehouse.getFood();
        List<Food> expected = List.of(milk3, meat1);
        assertThat(result, is(expected));
    }

    @Test
    public void thereThreeInShop() {
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.checkFood(foods);
        List<Food> result = shop.getFood();
        List<Food> expected = List.of(milk1, milk2, meat2);
        assertThat(result, is(expected));
    }

    @Test
    public void thereOneInTrash() {
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.checkFood(foods);
        List<Food> result = trash.getFood();
        List<Food> expected = List.of(milk4);
        assertThat(result, is(expected));
    }

    @Test
    public void productExpectationPercentIs90() {
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.checkFood(foods);
        assertThat(milk3.getExpirationPercent(), is(90));
    }

    @Test
    public void productDiscountIs15() {
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.checkFood(foods);
        assertThat(milk1.getDiscount(), is(15));
    }

    @Test
    public void showStoresList() {
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.checkFood(foods);
        List<StoreExporter> se = List.of(warehouse, shop, trash);
        for (StoreExporter store : se) {
            ConsoleStoreDisplay.display(store);
        }
    }

    @Test
    public void inTrashPriceIs0() {
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.checkFood(foods);
        assertThat(milk4.getPrice(), is(0.0));
    }

    @Test
    public void nothingChangesAfterResort() {
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.checkFood(foods);
        controlQuality.resort();
        List<Food> resultWarehouse = warehouse.getFood();
        List<Food> expectedWarehouse = List.of(milk3, meat1);
        assertThat(resultWarehouse, is(expectedWarehouse));
        List<Food> resultShop = shop.getFood();
        List<Food> expectedShop = List.of(milk1, milk2, meat2);
        assertThat(resultShop, is(expectedShop));
        List<Food> resultTrash = trash.getFood();
        List<Food> expectedTrash = List.of(milk4);
        assertThat(resultTrash, is(expectedTrash));
    }

    @Test
    public void changesIfStoresAfterChangedDatesAndResort() {
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.checkFood(foods);

        Calendar create = Calendar.getInstance();
        create.add(Calendar.DAY_OF_MONTH, -20);
        Calendar expected = Calendar.getInstance();
        expected.add(Calendar.DAY_OF_MONTH, +10);
        milk4.setCreateDate(create);
        milk4.setExpiredDate(expected);

        create = Calendar.getInstance();
        create.add(Calendar.DAY_OF_MONTH, -10);
        expected = Calendar.getInstance();
        expected.add(Calendar.DAY_OF_MONTH, +90);
        meat2.setCreateDate(create);
        meat2.setExpiredDate(expected);

        controlQuality.resort();

        List<Food> resultWarehouse = warehouse.getFood();
        List<Food> expectedWarehouse = List.of(milk3, meat1, meat2);
        assertThat(resultWarehouse, is(expectedWarehouse));
        List<Food> resultShop = shop.getFood();
        List<Food> expectedShop = List.of(milk1, milk2, milk4);
        assertThat(resultShop, is(expectedShop));
        List<Food> resultTrash = trash.getFood();
        List<Food> expectedTrash = List.of();
        assertThat(resultTrash, is(expectedTrash));
    }
}