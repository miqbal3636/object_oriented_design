package com.spsoft.parkinglot;

public class Car extends Vehicle {
    public Car(String licensePlate,int spotRequired){
        super(licensePlate,spotRequired);
    }
    public boolean canFitInSpot(Spot spot){
        return (spot.spotType == Spot.SpotType.COMPACT || spot.spotType == Spot.SpotType.LARGE);
    }
}
