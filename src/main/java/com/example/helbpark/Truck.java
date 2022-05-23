package com.example.helbpark;

public class Truck extends Vehicle{
    //camionnette
    private final int TICKET_PRICE = 30;
    private String vehicleType = "Truck";

    public Truck(String licencePlate)
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
