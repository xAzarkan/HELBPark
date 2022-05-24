package com.example.helbpark;

public class HalfPriceTruck implements PriceStrategy{

    double discount = 0.5; //50% de réduction

    @Override
    public double getTotalPrice(double basicPrice, String vehicleType, String licencePlate)
    {
        double totalPrice = basicPrice;

        if(vehicleType.equals("Truck"))
        {
            totalPrice = totalPrice * discount;
        }

        return totalPrice;
    }
}
