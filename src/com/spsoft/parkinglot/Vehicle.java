package com.spsoft.parkinglot;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    String licensePlate;
    int spotRequired;
    List<Spot> spots = new ArrayList<>();
    public Vehicle(String licensePlate, int spotRequired){
        this.licensePlate = licensePlate;
        this.spotRequired = spotRequired;
    }
    void clearSpots(){
        spots.clear();
        System.out.println("Size of spotTakens after clearing spots");
    }

    void assignSpot(Spot spot){
        spots.add(spot);
        System.out.println("Spot: "+spot.spotID+" has been assigned to Vehicle: "+ licensePlate);
    }
    public abstract boolean canFitInSpot(Spot spot);
}
