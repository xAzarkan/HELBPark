package com.example.helbpark;

public class ParkingPlace {

    private int placeNumber;
    private boolean isAvailable; //true si la place est libre, faux sinon
    private String vehicleType;
    private String licencePlate;

    private int pricePlace;

    public ParkingPlace(int placeNumber) {
        this.placeNumber = placeNumber;
        isAvailable = true; //libre par défaut
    }

    public ParkingPlace(int placeNumber, boolean isAvailable) {
        this.placeNumber = placeNumber;
        this.isAvailable = isAvailable;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean available) {
        isAvailable = available;
    }

    public void setInformations(String vehicleType, String licencePlate, int pricePlace){
        this.vehicleType = vehicleType;
        this.licencePlate = licencePlate;
        this.pricePlace = pricePlace;
    }

    public String getVehicleType()
    {
        return this.vehicleType;
    }

    public String getLicencePlate()
    {
        return this.licencePlate;
    }

    public void setPricePlace(int pricePlace){
        this.pricePlace = pricePlace;
    }

    public int getPricePlace()
    {
        return pricePlace;
    }

}
