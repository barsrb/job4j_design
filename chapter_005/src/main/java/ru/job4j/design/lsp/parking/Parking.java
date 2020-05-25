package ru.job4j.design.lsp.parking;

public interface Parking {
    boolean park(Vehicle vehicle);
    Vehicle leave(String number);
    int getFreePlaces();
}
