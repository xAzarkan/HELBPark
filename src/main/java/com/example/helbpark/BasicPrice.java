package com.example.helbpark;

public class BasicPrice implements PriceStrategy{

    @Override
    public double getTotalPrice(double basicPrice, String vehicleType, String licencePlate)
    {//prix de base

        double totalPrice = basicPrice;

        return totalPrice;
    }
}
