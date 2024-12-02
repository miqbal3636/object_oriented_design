package com.spsoft.parkinglot;

public class ParkingLotTest {

    public static void main(String args[]) {

        ParkingLot parkingLot = new ParkingLot(1,Configuration.Number_OF_LEVELS);

        Level level1 = parkingLot.createLevel(1,Configuration.level1Layout.length);
        level1.createSpotsForLevel(Configuration.level1Layout);
        parkingLot.addLevel(level1);

        Level level2 = parkingLot.createLevel(2,Configuration.level2Layout.length);
        level2.createSpotsForLevel(Configuration.level2Layout);
        parkingLot.addLevel(level2);

        Level level3 = parkingLot.createLevel(3,Configuration.level3Layout.length);
        level3.createSpotsForLevel(Configuration.level3Layout);
        parkingLot.addLevel(level3);
        parkingLot.printLayout();

        //create an object of Car and call the parkVehicle
        Vehicle car = new Car("C1",1);
        Ticket ticket1 = parkingLot.assignTicket(car);
        ticket1.printTicket();

        //create an object or MotorCycle and call parkVehicle
        Vehicle motorCycle = new MotorCycle("M1",1);
        Ticket ticket2 = parkingLot.assignTicket(motorCycle);
        ticket2.printTicket();

        Vehicle bus = new Bus("B1",5);
        Ticket ticket3 = parkingLot.assignTicket(bus);
        ticket3.printTicket();
        System.out.println();

        //Delaying 1 min for checkout.
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Checkout a vehicle
        Ticket ticket4 = parkingLot.ticketMap.get(3);
        System.out.println("Check out a vehicle with ticketNo : "+ticket4.ticketNo);
        parkingLot.checkoutVehicle(ticket4);
        ticket4.printTicket();


    }
}
