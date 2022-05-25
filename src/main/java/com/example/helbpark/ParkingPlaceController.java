package com.example.helbpark;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ParkingPlaceController {

    private ParkingPlace parkingPlace;
    private ParkingView parkingView;

    public ParkingPlaceController(ParkingPlace parkingPlace, ParkingView parkingView)
    {
        this.parkingPlace = parkingPlace;
        this.parkingView = parkingView;
    }
    public void setParkingPlaceAvailability(boolean availability)
    {
        parkingPlace.setAvailability(availability);
    }
    public boolean getParkingPlaceAvailability()
    {
        return parkingPlace.isAvailable();
    }
    public int getParkingPlaceNumber()
    {
        return parkingPlace.getPlaceNumber();
    }
    public String getParkingPlaceLicencePlate(){ return parkingPlace.getLicencePlate(); };
    public String getParkingPlaceVehicleType() {
        return parkingPlace.getVehicleType();
    }
    public String getParkingPlaceDiscountType() {
        return parkingPlace.getDiscountType();
    }
    public double getParkingPlacePricePlace(){return parkingPlace.getPricePlace();}

    public GameTicket getParkingPlaceGameTicket(){ return parkingPlace.getGameTicket(); }


    public void updateView() {
        String placeNumber = String.valueOf(parkingPlace.getPlaceNumber());
        Boolean available = parkingPlace.isAvailable();
        String vehicleType = parkingPlace.getVehicleType();
        String licencePlate = parkingPlace.getLicencePlate();

        parkingView.updateParkingPlace(this, placeNumber, available, vehicleType, licencePlate); //ça évite à la vue de connaitre le modèle
    }

    public void onButtonClick() {
        //ce qu'il se passe lorsque l'on clique sur une place de parking
        final int BUTTON_WIDTH = 200;
        final int BUTTON_HEIGHT = 30;
        final int LAYOUT_HEIGHT = 200;
        final String STYLE_LABEL = "-fx-font-size: 21px;";

        final String vehicleType = parkingPlace.getVehicleType();
        final String licencePlate = parkingPlace.getLicencePlate();
        final String placeNumber = String.valueOf(parkingPlace.getPlaceNumber());
        final String pricePlace = String.valueOf(parkingPlace.getPricePlace());

        String placeState = "";

        if(parkingPlace.isAvailable())
            placeState = "Free";
        else
            placeState = "Occupied";

        Label labelPlaceState = new Label("Status : " + placeState);
        Label labelPlaceNumber = new Label("Place number : " + placeNumber);
        Label labelVehicleType = new Label("Type of vehicle : " + vehicleType);
        Label labelLicencePlate = new Label("Licence plate : " + licencePlate);
        Label labelPricePlace = new Label("Total to pay : " + pricePlace + " €");

        labelPlaceNumber.setStyle(STYLE_LABEL);
        labelVehicleType.setStyle(STYLE_LABEL);
        labelLicencePlate.setStyle(STYLE_LABEL);
        labelPlaceState.setStyle(STYLE_LABEL);
        labelPricePlace.setStyle(STYLE_LABEL);

        Button submitButton = new Button("Set the place free");
        submitButton.setPrefWidth(BUTTON_WIDTH);
        submitButton.setPrefHeight(BUTTON_HEIGHT);

        VBox mainLayout = new VBox(LAYOUT_HEIGHT);
        VBox layout;

        if(parkingPlace.isAvailable())
            layout = new VBox(labelPlaceState, labelPlaceNumber);
        else
            layout = new VBox(labelPlaceState, labelPlaceNumber, labelVehicleType, labelLicencePlate, labelPricePlace);

        layout.setAlignment(Pos.TOP_CENTER);

        mainLayout.getChildren().addAll(layout, submitButton);
        mainLayout.setAlignment(Pos.TOP_CENTER);

        Scene secondScene = new Scene(mainLayout, 400, 400);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Edit parking place");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(300);
        newWindow.setY(300);

        newWindow.show();

        submitButton.setOnAction(event -> {
            if(this.getParkingPlaceAvailability() == false)
            {
                this.setParkingPlaceAvailability(true);
                this.printTicket();
            }
            newWindow.close();
        });
    }

    public void printTicket() {

        final double TICKET_PRICE_FOR_MOTORCYCLE = 10;
        final double TICKET_PRICE_FOR_CAR = 20;
        final double TICKET_PRICE_FOR_TRUCK = 30;

        String basicPrice;
        String date;
        String placeNumber;
        String vehicleType;
        String licencePlate;
        String discountType;
        String totalPrice;
        String discountCode;
        String typeGameTicket;
        String valueGameTicket;
        String game;

        /* BASIC PRICE & VEHICLE TYPE*/

        vehicleType = this.getParkingPlaceVehicleType();

        if(vehicleType.equals("Motorcycle"))
        {
            basicPrice = String.valueOf(TICKET_PRICE_FOR_MOTORCYCLE);

        }
        else if(vehicleType.equals("Car"))
        {
            basicPrice = String.valueOf(TICKET_PRICE_FOR_CAR);
        }
        else
        {
            basicPrice = String.valueOf(TICKET_PRICE_FOR_TRUCK);
        }

        /* DATE */


        String currentDay = String.valueOf(LocalDate.now().getDayOfMonth()); //récupère le jour de la semaine
        String currentMonth = String.valueOf(LocalDate.now().getMonthValue());
        String currentYear = String.valueOf(LocalDate.now().getYear());

        date = currentDay + "/" + currentMonth + "/" + currentYear;

        /* PLACE NUMBER */

        placeNumber = String.valueOf(this.getParkingPlaceNumber());

        /* LICENCE PLATE */

        licencePlate = this.getParkingPlaceLicencePlate();

        /* DISCOUNT TYPE */

        discountType = this.getParkingPlaceDiscountType();

        /* TOTAL PRICE */

        totalPrice = String.valueOf(this.getParkingPlacePricePlace());

        /* DISCOUNT CODE */

        discountCode = this.getParkingPlaceGameTicket().getDiscountCode();

        /* TYPE GAME TICKET */

        typeGameTicket = this.getParkingPlaceGameTicket().getTypeGameTicket();

        /* VALUE GAME TICKET */

        valueGameTicket = String.valueOf(this.getParkingPlaceGameTicket().getDiscountValue());

        /* GAME */

        game = this.getParkingPlaceGameTicket().getGame();

        Receipt receipt = new Receipt(basicPrice, date, placeNumber, vehicleType, licencePlate, discountType, totalPrice, discountCode, typeGameTicket, valueGameTicket, game);
        receipt.printReceipt();
    }
}
