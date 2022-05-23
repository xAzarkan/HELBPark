package com.example.helbpark;

public abstract class Vehicle {
    protected String licencePlate;

    public Vehicle(String licencePlate)
    {
        this.licencePlate = licencePlate;
    }

    public abstract String getVehicleType();

    public abstract int getTicketPrice();

    public void setLicencePlate(String licencePlate)
    {
        this.licencePlate = licencePlate;
    }

    public String getLicencePlate(){
        return this.licencePlate;
    }

}
