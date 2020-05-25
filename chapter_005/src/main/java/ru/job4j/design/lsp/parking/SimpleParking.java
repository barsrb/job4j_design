package ru.job4j.design.lsp.parking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SimpleParking implements Parking {
    private final Map<String, Vehicle> parkedVehicles = new HashMap<>();
    private final Map<Vehicle, Integer> startPlaceNumber = new HashMap<>();
    private final int[] places;

    public SimpleParking(int parkingPlaces) {
        places = new int[parkingPlaces];
    }

    @Override
    public boolean park(Vehicle vehicle) {
        if (parkedVehicles.containsKey(vehicle.getNumber())) {
            return false;
        }

        int parkingPlace = getFreePlace(vehicle);

        if (parkingPlace < 0) {
            return false;
        } else {
            parkVehicle(vehicle, parkingPlace);
            return true;
        }
    }

    private void parkVehicle(Vehicle vehicle, int parkingPlace) {
        parkedVehicles.put(vehicle.getNumber(), vehicle);
        startPlaceNumber.put(vehicle, parkingPlace);
        for (int i = 0; i < vehicle.getSize(); i++) {
            places[parkingPlace + i] = 1;
        }
    }

    private int getFreePlace(Vehicle vehicle) {
        int freePlaces = 0;
        int startPlace = -1;
        for (int placeNumber = 0; placeNumber < places.length; placeNumber++) {
            if (places[placeNumber] == 0) {
                freePlaces++;
                if (freePlaces == vehicle.getSize()) {
                    startPlace = placeNumber - vehicle.getSize() + 1;
                    break;
                }
            } else  {
                freePlaces = 0;
            }
        }
        return startPlace;
    }

    @Override
    public Vehicle leave(String number) {
        if (!parkedVehicles.containsKey(number)) {
            throw new IllegalArgumentException();
        }
        Vehicle v = parkedVehicles.get(number);
        parkedVehicles.remove(number);
        int startParkingPlace = startPlaceNumber.get(v);
        for (int i = 0; i < v.getSize(); i++) {
            places[startParkingPlace + i] = 0;
        }
        startPlaceNumber.remove(v);
        return v;
    }

    @Override
    public int getFreePlaces() {
        return (int) Arrays.stream(places).filter(place -> place == 0).count();
    }

}
