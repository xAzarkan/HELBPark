package com.example.helbpark;

public class ParkingPlace {

    private int placeNumber;
    private boolean isAvailable; //true si la place est libre, faux sinon
    private String vehicleType;
    private String licencePlate;
    private double pricePlace;
    private String discountType;
    private GameTicket gameTicket;

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

    public void setInformations(String vehicleType, String licencePlate, double pricePlace, String discountType, GameTicket gameTicket){
        this.vehicleType = vehicleType;
        this.licencePlate = licencePlate;
        this.pricePlace = pricePlace;
        this.discountType = discountType;
        this.gameTicket = gameTicket;
    }

    public String getVehicleType()
    {
        return this.vehicleType;
    }

    public String getLicencePlate()
    {
        return this.licencePlate;
    }

    public double getPricePlace()
    {
        return pricePlace;
    }

    public String getDiscountType(){ return discountType; }

    public GameTicket getGameTicket(){
        return gameTicket;
    }

}
