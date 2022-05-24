package com.example.helbpark;

public class Receipt {
    //ticket de caisse

    private String basicPrice;
    private String date;
    private String placeNumber;
    private String vehicleType;
    private String licencePlate;
    private String discountType;
    private String totalPrice;
    private String discountCode;
    private String typeGameTicket;
    private String valueGameTicket;

    public Receipt(String basicPrice, String date, String placeNumber, String vehicleType, String licencePlate, String discountType, String totalPrice, String discountCode, String typeGameTicket, String valueGameTicket) {

        this.basicPrice = basicPrice; //prix de base
        this.date = date; //date
        this.placeNumber = placeNumber; //place occupée
        this.vehicleType = vehicleType; //type de véhicule
        this.licencePlate = licencePlate; //plaque d'immatriculation
        this.discountType = discountType; //type de réduction
        this.totalPrice = totalPrice; //total à payer
        this.discountCode = discountCode; //code promo ciné
        this.typeGameTicket = typeGameTicket;
        this.valueGameTicket = valueGameTicket;
    }

    public void printReceipt()
    {
        System.out.println("Date: " + date);
        System.out.println("Place number: " + placeNumber);
        System.out.println("Vehicle type: " + vehicleType);
        System.out.println("Licence plate: " + licencePlate);
        System.out.println("Base price: " + basicPrice + "€");
        System.out.println("Discount : " + discountType);
        System.out.println("Total to pay : " + totalPrice);
        System.out.println("Discount code cinema : " + discountCode);
        System.out.println(typeGameTicket + " : Valeur : " + valueGameTicket + "%");
    }
}
