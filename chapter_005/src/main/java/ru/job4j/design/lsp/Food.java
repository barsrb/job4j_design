package ru.job4j.design.lsp;

import java.util.Calendar;

public abstract class Food {
    private String name;
    private Calendar expiredDate;
    private Calendar createDate;
    private Double price;
    private int discount;

    public void setName(String name) {
        this.name = name;
    }
    public void setExpiredDate(Calendar expiredDate) {
        this.expiredDate = expiredDate;
    }
    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public Double getPrice() {
        return price;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int i) {
        discount = i;
    }


    public int getExpirationPercent() {
        long expired = Calendar.getInstance().getTimeInMillis() - createDate.getTimeInMillis();
        long fullTime = expiredDate.getTimeInMillis() - createDate.getTimeInMillis();
        long toExpire = expiredDate.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        if (toExpire <= 0) {
            return 0;
        }
        return 100 - (int) (((expired * 100) / fullTime) % 100);
    }

    final void moveFood(Store store) {
        store.move(this);
    }
}
