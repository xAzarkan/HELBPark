package com.example.helbpark;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Random;

public class ParkingPlaceController {

    private final int BUTTON_WIDTH = 250;
    private final int BUTTON_HEIGHT = 30;
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT= 600;
    private final int POS_WINDOW_X = 300;
    private final int POS_WINDOW_Y = 300;
    private final String STYLE_LABEL = "-fx-font-size: 21px;";
    private final String STYLE_BUTTON = "-fx-font-size: 21px;";
    private final int SPACE_BETWEEN_BUTTON = 30;
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
    public void setParkingPlaceLicencePlate(String licencePlate) {
        parkingPlace.setLicencePlate(licencePlate);
    }
    public String getParkingPlaceDiscountType() {
        return parkingPlace.getDiscountType();
    }
    public double getParkingPlacePricePlace(){return parkingPlace.getPricePlace();}
    public void setParkingPlacePrice(double price) {
        parkingPlace.setPricePlace(price);
    }
    public void setParkingPlaceVehicle(Vehicle vehicle){ parkingPlace.setVehicle(vehicle); }
    public Vehicle getParkingPlaceVehicle()
    {
        return parkingPlace.getVehicle();
    }
    public void setParkingPlaceDiscountType(String discountType)
    {
        parkingPlace.setDiscountType(discountType);
    }
    public void updateView() {
        //méthode permettant de mettre à jour la vue (du Parking)
        String placeNumber = String.valueOf(this.getParkingPlaceNumber());
        Boolean available = this.getParkingPlaceAvailability();

        String vehicleType = "";
        String licencePlate = "";

        if(!available)
        {
            Vehicle vehicle = this.getParkingPlaceVehicle();
            this.setParkingPlacePrice(calculateTotalPrice(vehicle)); //calcul du prix de la place

            vehicleType = this.getParkingPlaceVehicleType();
            licencePlate = this.getParkingPlaceLicencePlate();
        }

        parkingView.updateParkingPlace(this, placeNumber, available, vehicleType, licencePlate);
    }
    public void onButtonClick() {
        //ce qu'il se passe lorsque l'on clique sur une place de parking
        String vehicleType = "";
        String licencePlate = "";
        String placeNumber = String.valueOf(this.getParkingPlaceNumber());
        String pricePlace = String.valueOf(this.getParkingPlacePricePlace());
        boolean isAvailable = this.getParkingPlaceAvailability();

        if(!isAvailable)
        {
            vehicleType = this.getParkingPlaceVehicleType();
            licencePlate = this.getParkingPlaceLicencePlate();
        }

        String placeState = "";

        if(isAvailable)
            placeState = "Free";
        else
            placeState = "Occupied";

        Label labelPlaceState = new Label("Status : " + placeState);
        Label labelPlaceNumber = new Label("Place number : " + placeNumber);
        Label labelVehicleType = new Label("Type of vehicle : " + vehicleType);
        Label labelLicencePlate = new Label("Licence plate : " + licencePlate);
        Label labelPricePlace = new Label("Total to pay : " + pricePlace + " € \n");

        labelPlaceNumber.setStyle(STYLE_LABEL);
        labelVehicleType.setStyle(STYLE_LABEL);
        labelLicencePlate.setStyle(STYLE_LABEL);
        labelPlaceState.setStyle(STYLE_LABEL);
        labelPricePlace.setStyle(STYLE_LABEL);

        Button submitButton = new Button("Set the place free");
        Button editVehicleTypeButton = new Button("Edit vehicle type");
        Button editLicencePlateButton = new Button("Edit licence plate");

        submitButton.setPrefWidth(BUTTON_WIDTH);
        submitButton.setPrefHeight(BUTTON_HEIGHT);
        submitButton.setStyle(STYLE_BUTTON);

        editVehicleTypeButton.setPrefWidth(BUTTON_WIDTH);
        editVehicleTypeButton.setPrefHeight(BUTTON_HEIGHT);
        editVehicleTypeButton.setStyle(STYLE_BUTTON);

        editLicencePlateButton.setPrefWidth(BUTTON_WIDTH);
        editLicencePlateButton.setPrefHeight(BUTTON_HEIGHT);
        editLicencePlateButton.setStyle(STYLE_BUTTON);

        VBox mainLayout = new VBox();
        VBox layoutButtons = new VBox(editVehicleTypeButton, editLicencePlateButton, submitButton);
        layoutButtons.setSpacing(SPACE_BETWEEN_BUTTON);
        layoutButtons.setAlignment(Pos.BASELINE_CENTER);

        VBox layout;

        if(isAvailable)
            layout = new VBox(labelPlaceState, labelPlaceNumber);
        else
            layout = new VBox(labelPlaceState, labelPlaceNumber, labelVehicleType, labelLicencePlate, labelPricePlace, layoutButtons);


        layout.setAlignment(Pos.BASELINE_CENTER);

        mainLayout.getChildren().add(layout);

        Scene secondScene = new Scene(mainLayout, WINDOW_WIDTH, WINDOW_HEIGHT);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Edit parking place");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(POS_WINDOW_X);
        newWindow.setY(POS_WINDOW_Y);

        newWindow.show(); //affichage de la fenêtre pour éditer la place de parking

        submitButton.setOnAction(event -> {
            this.setParkingPlaceAvailability(true); //libère la place
            this.printTicket(); //imprime le ticket de caisse
            newWindow.close();
        });

        editVehicleTypeButton.setOnAction(event -> {
            onEditVehicleTypeButtonClick();
            newWindow.close();
        });

        editLicencePlateButton.setOnAction(event -> {
            onEditLicencePlateButtonClick();
            newWindow.close();
        });
    }
    public void printTicket() {
    //fonction permettant créer un ticket de caisse (Receipt)
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

        vehicleType = this.getParkingPlaceVehicle().getVehicleType();

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

        GameTicket gameTicket = generateGameTicket(vehicleType); //génére un ticket jeu

        /* DATE */

        String currentDay = String.valueOf(LocalDate.now().getDayOfMonth()); //récupère le jour de la semaine
        String currentMonth = String.valueOf(LocalDate.now().getMonthValue());
        String currentYear = String.valueOf(LocalDate.now().getYear());

        date = currentDay + "/" + currentMonth + "/" + currentYear;

        /* PLACE NUMBER */

        placeNumber = String.valueOf(this.getParkingPlaceNumber());

        /* LICENCE PLATE */

        licencePlate = this.getParkingPlaceVehicle().getLicencePlate();

        /* DISCOUNT TYPE */

        discountType = this.getParkingPlaceDiscountType();

        /* TOTAL PRICE */

        totalPrice = String.valueOf(calculateTotalPrice(getParkingPlaceVehicle()));

        /* DISCOUNT CODE */

        discountCode = gameTicket.getDiscountCode();

        /* TYPE GAME TICKET */

        typeGameTicket = gameTicket.getTypeGameTicket();

        /* VALUE GAME TICKET */

        valueGameTicket = String.valueOf(gameTicket.getDiscountValue());

        /* GAME */

        game = gameTicket.getGame();

        Receipt receipt = new Receipt(basicPrice, date, placeNumber, vehicleType, licencePlate, discountType, totalPrice, discountCode, typeGameTicket, valueGameTicket, game);
        receipt.printReceipt(); //on enregistre le ticket créé
    }
    public double calculateTotalPrice(Vehicle vehicle) {
    //fonction permettant de calculer le prix total de la place en comptant les réductions du jour
        String discountType;
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
            discountType = "Wednesday : 25% discount for vehicles whose licence plate contains the letter P";
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

        totalPrice = placePrice.executePriceStrategy(vehicle); //éxecution de la stratégie de prix

        this.setParkingPlaceDiscountType(discountType); //type de réduction

        return totalPrice;
    }

    public GameTicket generateGameTicket(String vehicleType) {
        //méthode permettant de générer un ticket jeu --> retourne un GameTicket
        final int MAX_RANDOM_VALUE = 10;
        GameTicket gameTicket;

        Random random = new Random();
        int randomNumber = random.nextInt(MAX_RANDOM_VALUE); //génère un nombre entre 0 et 10

        int CHANCE = 4;

        if(randomNumber >= CHANCE){
            //chaque véhicule a plus de chance d'obtenir un certain ticket
            //si le nombre généré est plus grand ou égal à 4 alors c'est le ticket qui a le plus de chance d'être créé qui est créé
            if(vehicleType.equals("Motorcycle"))
                gameTicket = new StandardGameTicket();
            else if(vehicleType.equals("Car"))
                gameTicket = new SilverGameTicket();
            else
                gameTicket = new GoldGameTicket();
        }
        else{
            //si le nombre généré est plus petit que 4
            if(vehicleType.equals("Motorcycle")) {
                if(randomNumber%2 == 0) //si le nombre est pair
                    gameTicket = new SilverGameTicket();
                else //sinon
                    gameTicket = new GoldGameTicket();
            }
            else if(vehicleType.equals("Car")){
                if(randomNumber%2 == 0) //si le nombre est pair
                    gameTicket = new StandardGameTicket();
                else //sinon
                    gameTicket = new GoldGameTicket();
            }
            else{
                if(randomNumber%2 == 0) //si le nombre est pair
                    gameTicket = new StandardGameTicket();
                else //sinon
                    gameTicket = new SilverGameTicket();
            }
        }
        return gameTicket;
    }
    public void onEditVehicleTypeButtonClick() {
        int WINDOW_HEIGHT = 400;

        VBox mainLayout = new VBox();

        Label labelVehicleType = new Label("Select the vehicle type");
        labelVehicleType.setStyle(STYLE_LABEL);

        ComboBox vehicleTypeComboBox = new ComboBox();
        vehicleTypeComboBox.getItems().addAll("Motorcycle", "Car", "Truck");
        vehicleTypeComboBox.setStyle(STYLE_LABEL);

        VBox layoutVehicleType = new VBox(labelVehicleType, vehicleTypeComboBox);
        layoutVehicleType.setAlignment(Pos.CENTER);

        Button saveChanges = new Button("Save");
        saveChanges.setStyle(STYLE_LABEL);

        mainLayout.getChildren().addAll(layoutVehicleType, saveChanges);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(SPACE_BETWEEN_BUTTON);

        Scene secondScene = new Scene(mainLayout, WINDOW_WIDTH, WINDOW_HEIGHT);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Edit vehicle type");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(POS_WINDOW_X);
        newWindow.setY(POS_WINDOW_Y);

        newWindow.show(); //affichage de la fenêtre pour éditer la place de parking

        saveChanges.setOnAction(event -> {
            if(vehicleTypeComboBox.getValue() != null) //si l'utilisateur a choisi quelque chose
            {
                String licencePlate = this.getParkingPlaceLicencePlate(); //récupère la plaque d'immatriculation du véhicule
                String vehicleType = vehicleTypeComboBox.getValue().toString(); //récupère le type de véhicule choisi

                Vehicle vehicle = VehicleFactory.getInstance().create(vehicleType, licencePlate); //je créé un nouveau véhicule du type choisi

                this.setParkingPlaceVehicle(vehicle); //je réassigne le nouveau type de vehicule à la place de parking
                newWindow.close();
            }
        });
    }
    public void onEditLicencePlateButtonClick() {
        int WINDOW_HEIGHT = 400;

        VBox mainLayout = new VBox();

        Label labelLicencePlate = new Label("Licence plate (max 2 characters) :");
        labelLicencePlate.setStyle(STYLE_LABEL);

        TextField licencePlateText = new TextField();
        licencePlateText.setPromptText("Insert the licence plate (ex: C9)");
        licencePlateText.setStyle(STYLE_LABEL);

        VBox layoutVehicleType = new VBox(labelLicencePlate, licencePlateText);
        layoutVehicleType.setAlignment(Pos.CENTER);

        Button saveChanges = new Button("Save");
        saveChanges.setStyle(STYLE_LABEL);

        mainLayout.getChildren().addAll(layoutVehicleType, saveChanges);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(SPACE_BETWEEN_BUTTON);

        Scene secondScene = new Scene(mainLayout, WINDOW_WIDTH, WINDOW_HEIGHT);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Edit licence plate");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(POS_WINDOW_X);
        newWindow.setY(POS_WINDOW_Y);

        newWindow.show(); //affichage de la fenêtre pour éditer la place de parking

        saveChanges.setOnAction(event -> {
            int sizeOfLicencePlate = 2;

            if(licencePlateText.getLength() == sizeOfLicencePlate)
            {
                this.setParkingPlaceLicencePlate(licencePlateText.getText());
                newWindow.close();
            }

        });
    }
}

