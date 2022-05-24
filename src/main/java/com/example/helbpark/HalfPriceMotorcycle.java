package com.example.helbpark;

public class HalfPriceMotorcycle implements PriceStrategy{

    double discount = 0.5; //50% de réduction

    @Override
    public double getTotalPrice(double basicPrice, String vehicleType, String licencePlate)
    {
        double totalPrice = basicPrice;

        if(vehicleType.equals("Motorcycle")){
            totalPrice = totalPrice * discount;
        }

        return totalPrice; //return le prix de base
    }
}
