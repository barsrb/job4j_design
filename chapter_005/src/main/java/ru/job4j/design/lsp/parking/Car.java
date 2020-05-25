package ru.job4j.design.lsp.parking;

public class Car implements Vehicle {

    private final String number;

    public Car(String number) {
        this.number = number;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getNumber() {
        return number;
    }
}
