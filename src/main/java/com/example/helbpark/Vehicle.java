package com.example.helbpark;

public abstract class Vehicle {
    protected String licencePlate;
    public Vehicle(String licencePlate)
    {
        this.licencePlate = licencePlate;
    }
    public abstract String getVehicleType();
    public void setLicencePlate(String licencePlate)
    {
        this.licencePlate = licencePlate;
    }
    public String getLicencePlate(){
        return this.licencePlate;
    }

}
