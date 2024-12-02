package com.spsoft.parkinglot;

import java.time.LocalDateTime;

public class Spot {
    enum SpotType{
        MOTORCYCLE,COMPACT,LARGE
    }
    int spotID;
    public SpotType spotType;
    Vehicle vehicle;
    boolean isOccupied;
    LocalDateTime startTime;

    public Spot(int spotID,SpotType spotType){
        this.spotID = spotID;
        this.spotType = spotType;
    }
    public void reserveSpot(Vehicle vehicle, LocalDateTime startTime){
        this.isOccupied = true;
        this.vehicle = vehicle;
        this.startTime = startTime;
    }
    public void freeSpot(){
        this.isOccupied = false;
        this.vehicle = null;
    }
    public Spot getSpotById(int spotID){
        if(this.spotID == spotID)
            return this;
        else
            return null;
    }

    public com.spsoft.parkinglot.Spot.SpotType getSpotType() {
        return this.spotType;
    }

    public static Spot createSpot(int spotId, char type){
        Spot.SpotType currentSpot = switch (type) {
            case 'M' -> Spot.SpotType.MOTORCYCLE;
            case 'C' -> Spot.SpotType.COMPACT;
            case 'L' -> Spot.SpotType.LARGE;
            default -> null;
        };

        if(currentSpot == null)
            throw new IllegalArgumentException("Invalid Spot Type");

        return new Spot(spotId,currentSpot);
    }
}
