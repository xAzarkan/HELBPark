package com.example.helbpark;

import java.time.LocalDate;

public class HalfPricePairDay implements PriceStrategy{

    double discount = 0.5;

    @Override
    public double getTotalPrice(Vehicle vehicle)
    {
        LocalDate currentDate = LocalDate.now(); //récupère le jour actuelle

        int currentDay = currentDate.getDayOfMonth();

        double totalPrice = vehicle.getTicketPrice(); //prix de base

        if(currentDay%2 == 0) // si le jour est pair
        {
            totalPrice = totalPrice * discount;
        }

        return totalPrice;
    }
}
