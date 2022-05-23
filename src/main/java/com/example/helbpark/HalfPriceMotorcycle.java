package com.example.helbpark;

public class HalfPriceMotorcycle implements PriceStrategy{

    double discount = 0.5; //50% de réduction

    @Override
    public double getTotalPrice(Vehicle vehicle)
    {
        double totalPrice = vehicle.getTicketPrice();

        if(vehicle.getVehicleType().equals("Motorcycle")){
            totalPrice = totalPrice * discount;
        }

        return totalPrice; //return le prix de base
    }
}
