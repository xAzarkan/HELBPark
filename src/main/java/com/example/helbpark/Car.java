package com.example.helbpark;

public class Car extends Vehicle{
    //voiture
    private final int TICKET_PRICE = 20;
    private String vehicleType = "Car";

    public Car(String licencePlate)
    {
        super(licencePlate);
    }

    public String getVehicleType()
    {
        return vehicleType;
    }

    public int getTicketPrice()
    {
        return TICKET_PRICE;
    }
}
