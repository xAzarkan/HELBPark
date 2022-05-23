package com.example.helbpark;

public class BasicPrice implements PriceStrategy{

    @Override
    public double getTotalPrice(Vehicle vehicle)
    {//prix de base

        double totalPrice = vehicle.getTicketPrice();

        return totalPrice;
    }
}
