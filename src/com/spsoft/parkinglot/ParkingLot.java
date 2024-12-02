package com.spsoft.parkinglot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    int parkingLotId;
    int numLevels;
    private List<Level> levels;
    ConcurrentHashMap<Integer,Ticket> ticketMap = new ConcurrentHashMap<>();


    public ParkingLot(int parkingLotId,int numLevels){
        this.numLevels = numLevels;
        levels = new ArrayList<>(numLevels);
    }

    public void addLevel(Level level){
        this.levels.add(level);
    }

    public Ticket assignTicket(Vehicle vehicle){
        List<Spot> spots = parkVehicle(vehicle);
        if(spots == null)
            return null;
        LocalDateTime startTime = spots.get(0).startTime;
        Ticket ticket = new Ticket(vehicle,spots,startTime);
        ticketMap.put(ticket.ticketNo,ticket);
        return ticket;
    }

    public List<Spot> parkVehicle(Vehicle vehicle){
        for(Level level: levels){
            List<Spot> spots = level.assignSpots(vehicle);
            if(spots != null)
                return spots;
        }
        return null;
    }

    public Level createLevel(int levelId,int numRows){
        return new Level(levelId,numRows);
    }

    public boolean checkoutVehicle(Ticket ticket){
        List<Spot> spots = ticket.spots;
        spots.forEach(Spot::freeSpot);
        ticket.vehicle.clearSpots();
        ticket.setEndTime(LocalDateTime.now());
        ticket.calculateAmount();
        return true;
    }

    public void printLayout(){
        for(Level level : levels){
            System.out.println("Level :"+level.levelId);
            level.printLayoutForLevel();
        }
    }

}
