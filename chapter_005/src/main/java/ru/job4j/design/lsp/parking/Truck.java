package ru.job4j.design.lsp.parking;

public class Truck implements Vehicle {
    private final String number;

    public Truck(String number) {
        this.number = number;
    }

    private int size;

    public Truck(String number, int size) {
        this.number = number;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getNumber() {
        return number;
    }
}
