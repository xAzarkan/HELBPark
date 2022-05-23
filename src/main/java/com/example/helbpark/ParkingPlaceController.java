package com.example.helbpark;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    public void setParkingPlaceNumber(int placeNumber)
    {
        parkingPlace.setPlaceNumber(placeNumber);
    }

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
        {
            layout = new VBox(labelPlaceState, labelPlaceNumber);
            layout.setAlignment(Pos.TOP_CENTER);
        }
        else{
            layout = new VBox(labelPlaceState, labelPlaceNumber, labelVehicleType, labelLicencePlate, labelPricePlace);
            layout.setAlignment(Pos.TOP_CENTER);
        }

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
            this.setParkingPlaceAvailability(true);
            newWindow.close();
        });
    }

}
