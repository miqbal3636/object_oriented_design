package com.spsoft.parkinglot;

import java.time.LocalDateTime;
import java.util.*;

public class Level {
    int levelId;
    List<List<Spot>> rowList;
    int numRows;

    public Level(int levelId, int numRows){
        this.levelId = levelId;
        this.numRows = numRows;
        rowList = new ArrayList<>(numRows);
        for(int i=0; i < numRows; i++){
            rowList.add(new ArrayList<Spot>());
        }
    }
    public void addSpot(int rowNum, Spot spot){
        if(rowNum >= rowList.size())
            throw new IllegalArgumentException("Invalid Row Number: "+ rowNum);
        this.rowList.get(rowNum).add(spot); //since rowList is 0 based array and row number is starting from 1
    }

    public List<Spot> assignSpots(Vehicle vehicle){
        List<Spot> spots = findAvailableSpots(vehicle);
        if(spots == null)
            return null;
        LocalDateTime startTime = LocalDateTime.now();
        for(Spot sp : spots){
            sp.reserveSpot(vehicle,startTime);
        }
        return spots;
    }

    public List<Spot> findAvailableSpots(Vehicle vehicle){
        for(List<Spot> spotList : rowList){
            for(int spotIndex =0;  spotIndex < spotList.size(); spotIndex++){
                Spot spot = spotList.get(spotIndex);
                if(!spot.isOccupied && vehicle.canFitInSpot(spot)){
                    if(vehicle.spotRequired == 1)
                        return Collections.singletonList(spot);
                    else{ // For Bus it will require more than 1 spots
                        int endIndex = spotIndex + vehicle.spotRequired -1;
                        if(isConsecutiveFreeSpotsExists(spotList,spotIndex,endIndex,spot)){
                            return spotList.subList(spotIndex,endIndex+1);
                        }
                    }
                }

            }
        }
        return null;
    }

    private boolean isConsecutiveFreeSpotsExists(List<Spot> spotList, int spotIndex,int endIndex, Spot spot){
        if(endIndex >= spotList.size())
            return false;
        for(int i= spotIndex; i <= endIndex; i++){
            if(spotList.get(i).isOccupied)
                return false;
            if(spotList.get(i).spotType != spot.spotType)
                return false;
        }
        return true;
    }

    public void printLayoutForLevel(){
        for(List<Spot> row : rowList){
            for(Spot spot : row){
                System.out.print(spot.spotType.toString().charAt(0)+" ");
            }
            System.out.println();
        }
    }

    public void createSpotsForLevel(char [][] levelLayout){
        int numRows = levelLayout.length;
        int spotId=1;
        for(int row =0; row < numRows; row++){
            for(int col=0; col < levelLayout[row].length; col++){
                addSpot(row, Spot.createSpot(spotId++,levelLayout[row][col]));
            }
        }
    }
}
