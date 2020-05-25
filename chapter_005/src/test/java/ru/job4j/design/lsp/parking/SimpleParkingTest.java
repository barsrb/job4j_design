package ru.job4j.design.lsp.parking;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SimpleParkingTest {

    Parking simpleParking;

    @Before
    public void createParking() {
        simpleParking = new SimpleParking(10);
    }

    @Test
    public void addCarToParking() {
        assertTrue(simpleParking.park(new Car("car1")));
    }

    @Test
    public void addCarInFilledParkingReturnsFalse() {
        simpleParking.park(new Car("car1"));
        simpleParking.park(new Car("car2"));
        simpleParking.park(new Car("car3"));
        simpleParking.park(new Car("car4"));
        simpleParking.park(new Car("car5"));
        simpleParking.park(new Car("car6"));
        simpleParking.park(new Car("car7"));
        simpleParking.park(new Car("car8"));
        simpleParking.park(new Car("car9"));
        simpleParking.park(new Car("car10"));
        assertFalse(simpleParking.park(new Car("car11")));
    }

    @Test
    public void threeCarUseThreePlaces() {
        simpleParking.park(new Car("car1"));
        simpleParking.park(new Car("car2"));
        simpleParking.park(new Car("car3"));
        assertThat(simpleParking.getFreePlaces(), is(7));
    }

    @Test
    public void truckUseHisSizePlaces() {
        simpleParking.park(new Truck("truck1", 4));
        assertThat(simpleParking.getFreePlaces(), is(6));
    }

    @Test
    public void combinedVehiclesPlacesUsage() {
        simpleParking.park(new Truck("truck1", 6));
        simpleParking.park(new Car("car1"));
        assertThat(simpleParking.getFreePlaces(), is(3));
    }

    @Test
    public void parkTruckInEmptyParking() {
        assertTrue(simpleParking.park(new Truck("Truck1", 5)));
    }

    @Test
    public void cannotPartTruck() {
        simpleParking.park(new Car("Car1"));
        simpleParking.park(new Car("Car2"));
        simpleParking.park(new Car("Car3"));
        simpleParking.park(new Car("Car4"));
        simpleParking.park(new Car("Car5"));
        assertFalse(simpleParking.park(new Truck("Truck1", 6)));
    }


    @Test
    public void leaveCarFromParking() {
        Vehicle truck1 = new Truck("Truck1", 5);
        simpleParking.park(truck1);
        Vehicle leaved = simpleParking.leave("Truck1");
        assertThat(truck1, is(leaved));
    }

    @Test
    public void leaveCarAndFreePlace() {
        Vehicle truck1 = new Truck("Truck1", 5);
        simpleParking.park(truck1);
        simpleParking.leave("Truck1");
        assertThat(simpleParking.getFreePlaces(), is(10));
    }

    @Test (expected = IllegalArgumentException.class)
    public void tryToLeaveWhenParNotParked() {
        simpleParking.park(new Car("car1"));
        simpleParking.leave("wrong_number");
    }

    @Test
    public void whenThereIsNoPlaceForTruck() {
        Vehicle truck1 = new Truck("Truck1", 4);
        Vehicle truck2 = new Truck("Truck2", 4);
        simpleParking.park(truck1);
        simpleParking.park(truck2);
        simpleParking.leave("Truck1");
        assertThat(simpleParking.getFreePlaces(), is(6));
        assertFalse(simpleParking.park(new Truck("Truck3", 5)));

    }

    @Test
    public void whenTherePlaceForTruckBetweenCars() {
        simpleParking.park(new Car("Car1"));
        simpleParking.park(new Car("Car2"));
        simpleParking.park(new Car("Car3"));
        simpleParking.park(new Car("Car4"));
        simpleParking.park(new Car("Car5"));
        simpleParking.park(new Car("Car6"));
        simpleParking.park(new Car("Car7"));
        simpleParking.park(new Car("Car8"));
        simpleParking.park(new Car("Car9"));
        simpleParking.park(new Car("Car10"));
        simpleParking.leave("Car3");
        simpleParking.leave("Car4");
        simpleParking.leave("Car5");
        simpleParking.leave("Car6");
        assertThat(simpleParking.getFreePlaces(), is(4));
        assertTrue(simpleParking.park(new Truck("Truck1", 4)));
    }

    @Test
    public void whenCanNotParkTruckBetweenCars() {
        simpleParking.park(new Car("Car1"));
        simpleParking.park(new Car("Car2"));
        simpleParking.park(new Car("Car3"));
        simpleParking.park(new Car("Car4"));
        simpleParking.park(new Car("Car5"));
        simpleParking.park(new Car("Car6"));
        simpleParking.park(new Car("Car7"));
        simpleParking.park(new Car("Car8"));
        simpleParking.park(new Car("Car9"));
        simpleParking.park(new Car("Car10"));
        simpleParking.leave("Car3");
        simpleParking.leave("Car4");
        simpleParking.leave("Car6");
        simpleParking.leave("Car7");
        assertThat(simpleParking.getFreePlaces(), is(4));
        assertFalse(simpleParking.park(new Truck("Truck1", 4)));
    }

}