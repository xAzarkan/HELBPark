package com.example.helbpark;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.*;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Board {

    private final int NUMBER_OF_PLACES = 20;
    private ArrayList<ParkingPlace> parking;
    private ArrayList<ParkingPlaceController> parkingPlaceControllers; //chaque place de parking possède son controller
    private ParkingView parkingView;
    private ArrayList<String> simFileContent;
    public int indexOfSimFileContent = 0;
    public int elapsedSeconds = 0;

    public Board()
    {
        initApp();
        readSimFileContent();
    }

    public void initApp() {

        parking = new ArrayList<>();
        parkingPlaceControllers = new ArrayList<>();

        //création des places de parking
        for(int placeNumber = 1; placeNumber <= NUMBER_OF_PLACES; placeNumber++)
        {
            parking.add(new ParkingPlace(placeNumber));
        }

        //création de la vue permettant d'afficher le parking
        parkingView = new ParkingView();

        //création d'un controller pour chaque place de parking
        for(ParkingPlace parkingPlace : parking)
        {
            parkingPlaceControllers.add(new ParkingPlaceController(parkingPlace, parkingView));
            parkingView.initParkingView(String.valueOf(parkingPlace.getPlaceNumber()));
        }

        updateParkingView();
    }

    public void readSimFileContent() {
        //lecture du fichier de simulation

        try{
            //String simFilePath = new File("simfile.txt").getAbsolutePath();
            File file = new File("simfile.txt");

            BufferedReader buffReader = new BufferedReader(new FileReader(file));

            String line;

            simFileContent = new ArrayList<>();

            while ((line = buffReader.readLine()) != null)
            {//lecture du fichier ligne par ligne

                simFileContent.add(line);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        createVehicles();
    }

    public void createVehicles() {
        //cette fonction permet de créer des véhicules à partir de ce qui a été récupérer
        //du fichier de simulation

        Timeline everySecondTimeLine = new Timeline(
                new KeyFrame(Duration.seconds(1), //toutes les secondes
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {

                                updateParkingView();

                                String[] columns = simFileContent.get(indexOfSimFileContent).split(","); //séparateur de colonne : ","

                                    String time = columns[0];
                                    String vehicleType = columns[1];
                                    String licencePlate = columns[2];

                                    int timeInterval = Integer.parseInt(time);

                                    if(elapsedSeconds == timeInterval)
                                    {
                                        Vehicle vehicle = VehicleFactory.getInstance().create(vehicleType, licencePlate);
                                        parkVehicle(vehicle);

                                        elapsedSeconds = 0;
                                        indexOfSimFileContent++;
                                    }
                                    elapsedSeconds++;
                                }
                        }));
        everySecondTimeLine.setCycleCount(Timeline.INDEFINITE);

        everySecondTimeLine.play();
    }

    public void parkVehicle(Vehicle vehicle) {
        //cette fonction permet de garer un vehicule dans une place qui est libre

        for(ParkingPlace parkingPlace : parking)
        { //parcours toutes les places de parking
            if(parkingPlace.isAvailable())
            { //si une place est libre
                parkingPlace.setAvailability(false);
                parkingPlace.setVehicle(vehicle);
                break;
            }
        }

        updateParkingView();
    }

    public void updateParkingView() {
        //mise à jour de la vue avec le controller
        for(ParkingPlaceController parkingPlaceController : parkingPlaceControllers)
        {
            parkingPlaceController.updateView();
        }
    }

    public GridPane getLayout()
    {
        return parkingView.getLayout();
    }

}
