package com.example.helbpark;

public class Truck extends Vehicle{
    //camionnette
    private String vehicleType = "Truck";

    public Truck(String licencePlate)
    {
        super(licencePlate);
    }

    public String getVehicleType()
    {
        return vehicleType;
    }

}
