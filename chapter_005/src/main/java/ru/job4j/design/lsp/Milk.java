package ru.job4j.design.lsp;

import java.util.Calendar;

public class Milk extends Food {
    public Milk(String name, Calendar createDate, Double price) {
        setName(name);
        setCreateDate(createDate);
        Calendar expired = Calendar.getInstance();
        expired.setTime(createDate.getTime());
        expired.add(Calendar.DAY_OF_MONTH, 30);
        setExpiredDate(expired);
        setPrice(price);
    }

}
