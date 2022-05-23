package com.example.helbpark;

public class Bill {

    private PriceStrategy priceStrategy;

    public Bill(PriceStrategy priceStrategy){
        this.priceStrategy = priceStrategy;
    }

    public double executePriceStrategy(Vehicle vehicle){
        return priceStrategy.getTotalPrice(vehicle);
    }

}
