package ru.job4j.design.lsp;

import java.util.Calendar;

public class Meat extends Food {
    public Meat(String name, Calendar createDate, Calendar expiredDate, Double price) {
        setName(name);
        setCreateDate(createDate);
        setExpiredDate(expiredDate);
        setPrice(price);
    }
}
