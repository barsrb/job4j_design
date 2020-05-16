package ru.job4j.design.lsp.parking;

public class Parking {
    private int carPlaces;
    private int truckPlaces;

    public Parking(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (vehicle.getSize() == 1) {
            if (carPlaces == 0) {
                return false;
            } else {
                carPlaces--;
            }
        } else {
            if (truckPlaces > 0) {
                truckPlaces--;
            } else if (carPlaces >= vehicle.getSize()) {
                carPlaces -= vehicle.getSize();
            } else {
                return false;
            }
        }
        return true;
    }

    public int getCarPlaces() {
        return carPlaces;
    }

    public int getTruckPlaces() {
        return truckPlaces;
    }
}
