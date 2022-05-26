package com.example.helbpark;

import java.time.LocalDate;

public class HalfPriceEvenDay implements PriceStrategy{

    double discount = 0.5;

    @Override
    public double getTotalPrice(double basicPrice, String vehicleType, String licencePlate)
    {
        LocalDate currentDate = LocalDate.now();

        int currentDay = currentDate.getDayOfMonth(); //récupère le jour actuelle

        double totalPrice = basicPrice; //prix de base du véhicule

        if(currentDay%2 == 0) // si le jour est pair
        {
            totalPrice = totalPrice * discount;
        }

        return totalPrice;
    }
}
