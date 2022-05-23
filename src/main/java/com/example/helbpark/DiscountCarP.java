package com.example.helbpark;

public class DiscountCarP implements PriceStrategy{

    double discount = 0.25; //25% de reduction

    @Override
    public double getTotalPrice(Vehicle vehicle)
    {
        double totalPrice = vehicle.getTicketPrice();

        if(vehicle.getLicencePlate().contains("P"))
        { //plaque d'immatriculation contient la lettre P
            totalPrice = totalPrice * discount;
        }

        return totalPrice;
    }
}
