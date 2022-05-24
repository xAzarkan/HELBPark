package com.example.helbpark;

public class Car extends Vehicle{
    //voiture
    private String vehicleType = "Car";

    public Car(String licencePlate)
    {
        super(licencePlate);
    }

    public String getVehicleType()
    {
        return vehicleType;
    }
}
