package com.example.helbpark;

public class PlacePrice {
    private final double TICKET_PRICE_FOR_MOTORCYCLE = 10;
    private final double TICKET_PRICE_FOR_CAR = 20;
    private final double TICKET_PRICE_FOR_TRUCK = 30;
    private PriceStrategy priceStrategy;

    public PlacePrice(PriceStrategy priceStrategy){
        this.priceStrategy = priceStrategy;
    }

    public double executePriceStrategy(Vehicle vehicle){

        double basicPrice; //prix de base pour le véhicule (sans promo)
        String vehicleType = vehicle.getVehicleType();

        if(vehicleType.equals("Motorcycle"))
            basicPrice = TICKET_PRICE_FOR_MOTORCYCLE;
        else if(vehicleType.equals("Car"))
            basicPrice = TICKET_PRICE_FOR_CAR;
        else
            basicPrice = TICKET_PRICE_FOR_TRUCK;

        return priceStrategy.getTotalPrice(basicPrice, vehicleType, vehicle.getLicencePlate());
    }

}
