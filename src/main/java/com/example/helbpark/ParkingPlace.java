package com.example.helbpark;

public class ParkingPlace {

    private int placeNumber;
    private boolean isAvailable; //true si la place est libre, faux sinon
    private double pricePlace;
    private String discountType;
    private Vehicle vehicle;

    public ParkingPlace(int placeNumber) {
        this.placeNumber = placeNumber;
        isAvailable = true; //libre par défaut
    }

    public int getPlaceNumber() {
        return placeNumber;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailability(boolean available) {
        isAvailable = available;
    }
    public void setPricePlace(double pricePlace) {
        this.pricePlace = pricePlace;
    }
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }
    public void setLicencePlate(String licencePlate){
        this.vehicle.setLicencePlate(licencePlate);
    }
    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }
    public Vehicle getVehicle(){
        return vehicle;
    }
    public String getVehicleType() { return this.vehicle.getVehicleType(); }
    public String getLicencePlate()
    {
        return this.vehicle.getLicencePlate();
    }
    public double getPricePlace()
    {
        return pricePlace;
    }
    public String getDiscountType(){ return discountType; }
}
