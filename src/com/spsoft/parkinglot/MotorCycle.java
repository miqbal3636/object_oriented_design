package com.spsoft.parkinglot;

public class MotorCycle extends Vehicle {
    public MotorCycle(String licensePlate, int spotRequired) {
        super(licensePlate, 1);
    }
    public boolean canFitInSpot(Spot spot) {
        return true;
    }
}
