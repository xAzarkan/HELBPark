package com.example.helbpark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private String game;

    public Receipt(String basicPrice, String date, String placeNumber, String vehicleType, String licencePlate, String discountType, String totalPrice, String discountCode, String typeGameTicket, String valueGameTicket, String game) {

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
        this.game = game;
    }

    public void printReceipt() {
        Date dateToFormat = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        String folderName = dateFormat.format(dateToFormat);
        //System.out.println(folderName);

        String fileName = licencePlate;
        if(typeGameTicket.equals("Gold ticket"))
            fileName += "_gol.txt";
        else if(typeGameTicket.equals("Silver ticket"))
            fileName += "_sil.txt";
        else
            fileName += "_std.txt";

        //System.out.println(fileName);

        /*_________CREATION DU FICHIER TXT______________*/

        File folder = new File(folderName);

        if(!folder.exists()){
            folder.mkdir();
            System.out.println("folder created");
        }

        try {
            File file = new File(folderName, fileName);

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        /*_________WRITE IN THE FILE__________*/

        String contentOfReceipt = "Date: " + date + "\n"
                + "Place number: " + placeNumber + "\n"
                + "Vehicle type: " + vehicleType + "\n"
                + "Licence plate: " + licencePlate + "\n"
                + "Base price: " + basicPrice + "€" + "\n"
                + "Discount : " + discountType + "\n"
                + "Total to pay : " + totalPrice + "€" + "\n"
                + "Discount code cinema : " + discountCode + "\n"
                + typeGameTicket + " : Valeur : " + valueGameTicket + "%" + "\n";

        if(typeGameTicket.equals("Silver ticket") || typeGameTicket.equals("Gold ticket"))
            contentOfReceipt += "Game : \n" + game;

        try
        {
            FileWriter fileWriter = new FileWriter(new File(folderName, fileName));
            fileWriter.write(contentOfReceipt);
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
