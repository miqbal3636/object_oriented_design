package com.spsoft.parkinglot;

public class Bus extends Vehicle{
    public Bus(String licensePlate, int spotRequired){
        super(licensePlate,spotRequired);
    }
    public boolean canFitInSpot(Spot spot){
        return (spot.spotType == Spot.SpotType.LARGE);
    }
}
