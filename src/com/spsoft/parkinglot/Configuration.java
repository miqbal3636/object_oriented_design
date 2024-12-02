package com.spsoft.parkinglot;

import java.util.concurrent.atomic.AtomicInteger;

public class Configuration {

    public static final int Number_OF_LEVELS = 3;

    public static AtomicInteger ticketNumber = new AtomicInteger(1);

    public static final char [][] level1Layout = {
            {'L','L','L','L','L','L','C','C','C','C'},
            {'M','M','M','M','M','M','M','M','M','C'},
            {'C','C','C','C','C','C','C','L','L','L','L','L','L'}
    };

    public static final char [][] level2Layout = {
            {'M','M','M','L','L','L','L','L','L','C'},
            {'M','M','M','M','M','M','M','M','M','C'},
            {'C','C','C','C','C','C','C','L','L','L','L','L','L'},
            {'C','C','C','C','C','C','C','L','L','L','L','L','L'}
    };

    public static final char [][] level3Layout = {
            {'M','M','M','L','L','L','L','L','L','C'},
            {'C','C','C','C','C','C','C','C','C','M'},
            {'C','C','C','C','C','C','C','L','L','L','L','L','L'},
            {'L','L','L','L','L','L','L','L','L'},
            {'L','L','L','L','L','L','L','L','L'}
    };

    public static double getParkingRate(Spot.SpotType spotType){
        double rate = switch (spotType){
            case MOTORCYCLE -> 5.0;
            case COMPACT -> 8.0;
            case LARGE -> 10.0;
        };
        return rate;
    }
}
