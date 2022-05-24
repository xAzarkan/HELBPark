package com.example.helbpark;

public class Motorcycle extends Vehicle{
    //moto

    private String vehicleType = "Motorcycle";

    public Motorcycle(String licencePlate) {
        super(licencePlate);
    }

    public String getVehicleType()
    {
        return vehicleType;
    }


}
