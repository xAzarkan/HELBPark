package com.example.helbpark;

public class Motorcycle extends Vehicle{
    //moto
    private final int TICKET_PRICE = 10;
    private String vehicleType = "Motorcycle";

    public Motorcycle(String licencePlate)
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
