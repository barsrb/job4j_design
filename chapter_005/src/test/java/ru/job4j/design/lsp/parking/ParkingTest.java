package ru.job4j.design.lsp.parking;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ParkingTest {

    Parking parking;

    @Before
    public void createParking() {
        parking = new Parking(10, 5);
    }

    @Test
    public void addCarToParking() {
        assertTrue(parking.parkVehicle(new Car()));
    }

    @Test
    public void addCarInFilledParkingReturnsFalse() {
        parking.parkVehicle(new Car());
        parking.parkVehicle(new Car());
        parking.parkVehicle(new Car());
        parking.parkVehicle(new Car());
        parking.parkVehicle(new Car());
        parking.parkVehicle(new Car());
        parking.parkVehicle(new Car());
        parking.parkVehicle(new Car());
        parking.parkVehicle(new Car());
        parking.parkVehicle(new Car());
        assertFalse(parking.parkVehicle(new Car()));
    }

    @Test
    public void parkTruckInEmptyParking() {
        assertTrue(parking.parkVehicle(new Truck(5)));
    }

    @Test
    public void cannotPartTruck() {
        parking.parkVehicle(new Truck(5));
        parking.parkVehicle(new Truck(4));
        parking.parkVehicle(new Truck(5));
        parking.parkVehicle(new Truck(5));
        parking.parkVehicle(new Truck(5));
        assertFalse(parking.parkVehicle(new Truck(20)));
    }

    @Test
    public void canParkTruckInCarsParkingPlaces() {
        parking.parkVehicle(new Truck(5));
        parking.parkVehicle(new Truck(4));
        parking.parkVehicle(new Truck(5));
        parking.parkVehicle(new Truck(5));
        parking.parkVehicle(new Truck(5));
        parking.parkVehicle(new Truck(7));
        assertThat(parking.getCarPlaces(), is(3));
    }

}