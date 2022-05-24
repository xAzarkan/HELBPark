package com.example.helbpark;

public interface PriceStrategy {
    //application du patter stratégie
    double getTotalPrice(double basicPrice, String vehicleType, String licencePlate);
}
