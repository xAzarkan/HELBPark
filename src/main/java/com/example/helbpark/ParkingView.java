package com.example.helbpark;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ParkingView {
    private final int BUTTON_WIDTH = 120;
    private final int BUTTON_HEIGHT = 75;

    private final int NUMBER_OF_COLUMNS = 4;
    private final int GAP_BETWEEN_BUTTONS = 10;

    private final String FONT_SIZE = "-fx-font-size: 21px;";

    private final String STYLE_MOTORCYCLE_BUTTON = "-fx-background-color: #5fd9c3;" + FONT_SIZE;
    private final String STYLE_CAR_BUTTON = "-fx-background-color: #ff5959;" + FONT_SIZE;
    private final String STYLE_TRUCK_BUTTON = "-fx-background-color: #855bd7;" + FONT_SIZE;


    private GridPane layout;
    private int numberOfPlaces;

    private ArrayList<Button> listOfButtons;


    private String styleLayout = "-fx-background-color: #525151;";

    private String styleParkingGridPane = "-fx-background-color: black;" +
            "-fx-border-color: black;" +
            "-fx-border-width: 5px;";

    private String styleAvailableParkingPlace = "-fx-background-color: #7fd95f;" + FONT_SIZE;

    public void initParkingView(ArrayList<ParkingPlace> parkingPlaces){
        numberOfPlaces = parkingPlaces.size();

        layout = new GridPane();
        layout.setStyle(styleLayout);

        listOfButtons = new ArrayList<>();

        GridPane parkingGridPane = new GridPane();
        parkingGridPane.setStyle(styleParkingGridPane);
        parkingGridPane.setHgap(GAP_BETWEEN_BUTTONS);
        parkingGridPane.setVgap(GAP_BETWEEN_BUTTONS);

        for(ParkingPlace parkingPlace : parkingPlaces)
        {
            String placeNumber = String.valueOf(parkingPlace.getPlaceNumber());

            Button button = new Button(placeNumber);
            //vert car la place sera obligatoirement libre au début
            button.setStyle(styleAvailableParkingPlace);
            button.setId(placeNumber);

            button.setOnAction(e -> {
                onButtonClick(parkingPlace);
            });

            button.setPrefWidth(BUTTON_WIDTH);
            button.setPrefHeight(BUTTON_HEIGHT);

            listOfButtons.add(button);
        }

        int row = 0;
        int col = 0;

        for(Button button : listOfButtons)
        {
            parkingGridPane.add(button, col, row); //ajout du bouton dans le gridpane

            if(col < NUMBER_OF_COLUMNS)
            {
                col++;
            }
            else
            {
                row++;
                col = 0;
            }
        }

        layout.getChildren().add(parkingGridPane);
        layout.setAlignment(Pos.CENTER);
    }

    public void setButtonInformations(ParkingPlace parkingPlace, int placeNumber, String vehicleType, String licencePlate)
    {
        String style = "";

        for(Button button : listOfButtons)
        {
            if(button.getId().equals(String.valueOf(placeNumber)))
            {
                if(parkingPlace.isAvailable())
                {
                    button.setText(String.valueOf(placeNumber));
                    style = styleAvailableParkingPlace;

                }
                else
                {
                    button.setText(placeNumber + "\n" + licencePlate);

                    if(vehicleType.equals("Motorcycle"))
                    {
                        style = STYLE_MOTORCYCLE_BUTTON;
                    }
                    else if(vehicleType.equals("Car"))
                    {
                        style = STYLE_CAR_BUTTON;
                    }
                    else if(vehicleType.equals("Truck"))
                    {
                        style = STYLE_TRUCK_BUTTON;
                    }
                }

                button.setOnAction(e -> {
                    onButtonClick(parkingPlace);
                });

                button.setStyle(style);
            }
        }
    }

    public void onButtonClick(ParkingPlace parkingPlace)
    {
        final int BUTTON_WIDTH = 200;
        final int BUTTON_HEIGHT = 30;
        final int LAYOUT_HEIGHT = 200;
        final String STYLE_LABEL = FONT_SIZE;

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
            parkingPlace.setAvailability(true);
            newWindow.close();
        });
    }


    public GridPane getLayout()
    {
        return layout;
    }
}
