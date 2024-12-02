package com.spsoft.parkinglot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Ticket {
    int ticketNo;
    Vehicle vehicle;
    List<Spot> spots;
    LocalDateTime startTime;
    LocalDateTime endTime;
    boolean isPaid;
    double amount;

    public Ticket(Vehicle vehicle, List<Spot> spots, LocalDateTime startTime){
        this.vehicle = vehicle;
        this.spots = spots;
        this.startTime = startTime;
        ticketNo = Configuration.ticketNumber.getAndIncrement();
    }
    public long parkingDurationInMins(){
        if(endTime == null)
            return 0;
        return Duration.between(startTime,endTime).toMinutes();
    }
    public double calculateAmount(){
        double duration = parkingDurationInMins() * 1.0 /60;
        double amount=0;
        for(Spot spot : spots){
           amount += Configuration.getParkingRate(spot.spotType) * duration;
        }
        this.amount = amount;
        return amount;
    }
    public void printTicket(){
        System.out.println("Ticket Number: "+ ticketNo);
        System.out.println("Vehicle License Plate: "+vehicle.licensePlate);
        System.out.println("Start Time: "+ startTime);
        System.out.println("End Time: "+ endTime);
        System.out.println("Is Paid: "+ isPaid);
        System.out.println("Spots :"+ this.spots.stream().map(spot -> String.valueOf(spot.spotID)).collect(Collectors.joining(", ")));
        System.out.println("Amount: "+ amount);
        System.out.println();
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
