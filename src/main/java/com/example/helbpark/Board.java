package com.example.helbpark;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
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

    public String discountType;

    public Board()
    {
        initApp();
        readSimFileContent();
    }


    public void initApp()
    {
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
                new KeyFrame(Duration.seconds(1),
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
        {
            if(parkingPlace.isAvailable())
            {
                parkingPlace.setAvailability(false);

                String vehicleType = vehicle.getVehicleType();
                String licencePlate = vehicle.getLicencePlate();
                double ticketPrice = calculateTotalPrice(vehicle);

                /* Traduction des types de véhicules fr --> anglais */
                if(vehicleType.equals("moto"))
                    vehicleType = "Motorcycle";
                else if(vehicleType.equals("voiture"))
                    vehicleType = "Car";
                else if(vehicleType.equals("camionette"))
                    vehicleType = "Truck";
                /* -------------------------------------------------- */

                GameTicket gameTicket = generateGameTicket(vehicleType);

                parkingPlace.setInformations(vehicleType, licencePlate, ticketPrice, discountType, gameTicket);
                break;
            }
        }

        updateParkingView();
    }

    public GameTicket generateGameTicket(String vehicleType)
    {
        final int MAX_RANDOM_VALUE = 10;
        GameTicket gameTicket;

        Random random = new Random();
        int randomNumber = random.nextInt(MAX_RANDOM_VALUE);

        int CHANCE = 4;

        if(randomNumber >= CHANCE){
            //chaque véhicule a plus de chance d'obtenir un certain ticket
            if(vehicleType.equals("Motorcycle"))
                gameTicket = new StandardGameTicket();
            else if(vehicleType.equals("Car"))
                gameTicket = new SilverGameTicket();
            else
                gameTicket = new GoldGameTicket();
        }
        else{
            if(vehicleType.equals("Motorcycle")) {
                if(randomNumber%2 == 0)
                    gameTicket = new SilverGameTicket();
                else
                    gameTicket = new GoldGameTicket();
            }
            else if(vehicleType.equals("Car")){
                if(randomNumber%2 == 0)
                    gameTicket = new StandardGameTicket();
                else
                    gameTicket = new GoldGameTicket();
            }
            else{
                if(randomNumber%2 == 0)
                    gameTicket = new StandardGameTicket();
                else
                    gameTicket = new SilverGameTicket();
            }
        }
        return gameTicket;
    }

    public double calculateTotalPrice(Vehicle vehicle) {

        double totalPrice;

        String currentDay = LocalDate.now().getDayOfWeek().name(); //récupère le jour de la semaine

        PlacePrice placePrice;

        if(currentDay.equals("TUESDAY"))
        {
            placePrice = new PlacePrice(new HalfPriceMotorcycle());
            discountType = "Tuesday : Half price for motorcycles";
        }
        else if(currentDay.equals("WEDNESDAY"))
        {
            placePrice = new PlacePrice(new DiscountCarP());
            discountType = "Wednesday : 25% discount for vehicles whose license plate contains the letter P";
        }
        else if(currentDay.equals("FRIDAY"))
        {
            placePrice = new PlacePrice(new HalfPriceTruck());
            discountType = "Friday : Half price for trucks";
        }
        else if(currentDay.equals("SATURDAY"))
        {
            placePrice = new PlacePrice(new HalfPriceEvenDay());
            discountType = "Saturday : If the day is even, half price, otherwise base price.";
        }
        else
        {
            placePrice = new PlacePrice(new BasicPrice());
            discountType = currentDay + " : Base price";
        }

        totalPrice = placePrice.executePriceStrategy(vehicle);

        return totalPrice;
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
