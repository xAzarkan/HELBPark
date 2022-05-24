package com.example.helbpark;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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
    private ArrayList<Button> listOfButtons;

    private String styleLayout = "-fx-background-color: #525151;";

    private String styleParkingGridPane = "-fx-background-color: black;" +
            "-fx-border-color: black;" +
            "-fx-border-width: 5px;";

    private String styleAvailableParkingPlace = "-fx-background-color: #7fd95f;" + FONT_SIZE;

    public ParkingView(){
        listOfButtons = new ArrayList<>();
    }


    public void initParkingView(String placeNumber){

        layout = new GridPane();

        layout.setStyle(styleLayout);

        GridPane parkingGridPane = new GridPane();
        parkingGridPane.setStyle(styleParkingGridPane);
        parkingGridPane.setHgap(GAP_BETWEEN_BUTTONS);
        parkingGridPane.setVgap(GAP_BETWEEN_BUTTONS);


        Button button = new Button(placeNumber);
        //vert car la place sera obligatoirement libre au début

        button.setId(placeNumber);
        button.setPrefWidth(BUTTON_WIDTH);
        button.setPrefHeight(BUTTON_HEIGHT);

        listOfButtons.add(button);

        int row = 0;
        int col = 0;

        for(Button btn : listOfButtons)
        {
            parkingGridPane.add(btn, col, row); //ajout du bouton dans le gridpane

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

    public void updateParkingPlace(ParkingPlaceController parkingPlaceController, String placeNumber, Boolean isAvailable, String vehicleType, String licencePlate){

        String style = "";

        for(Button button : listOfButtons)
        {
            if(button.getId().equals(placeNumber))
            {
                if(isAvailable)
                {
                    button.setText(placeNumber);
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
                    parkingPlaceController.onButtonClick();
                });

                button.setStyle(style);
            }
        }
    }


    public GridPane getLayout()
    {
        return layout;
    }
}
