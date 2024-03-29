package com.example.helbpark;

public class DiscountCarP implements PriceStrategy{

    double discount = 0.75; //25% de reduction

    @Override
    public double getTotalPrice(double basicPrice, String vehicleType, String licencePlate)
    {
        double totalPrice = basicPrice;

        if(licencePlate.contains("P"))
        { //plaque d'immatriculation contient la lettre P
            totalPrice = totalPrice * discount;
        }

        return totalPrice;
    }
}
