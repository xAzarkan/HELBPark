package com.example.helbpark;

public class HalfPriceTruck implements PriceStrategy{

    double discount = 0.5; //50% de réduction

    @Override
    public double getTotalPrice(Vehicle vehicle)
    {
        double totalPrice = vehicle.getTicketPrice();

        if(vehicle.getVehicleType().equals("Truck"))
        {
            totalPrice = totalPrice * discount;
        }

        return totalPrice;
    }
}
