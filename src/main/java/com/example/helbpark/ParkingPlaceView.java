package com.example.helbpark;

public class ParkingPlaceView {

    private String styleAvailableParkingPlace = "-fx-background-color: #7fd95f;" +
                                                "-fx-font-size: 21px;";
    private String styleNotAvailableParkingPlace = "-fx-background-color: #ff5959;" + "-fx-font-size: 21px;";

    public void updateParkingPlace(ParkingPlace parkingPlace, ParkingView parkingView){

        //System.out.println("Numero : " + parkingPlace.getPlaceNumber() + "| Etat : " + parkingPlace.isAvailable());

        int placeNumber = parkingPlace.getPlaceNumber();

        if(parkingPlace.isAvailable()){
            //parkingView.setButton(parkingView.getButton(placeNumber), styleAvailableParkingPlace);
            parkingView.setButtonInformations(parkingPlace, placeNumber, "", "");
        }else{
            String vehicleType = parkingPlace.getVehicleType();
            String licencePlate = parkingPlace.getLicencePlate();

            //parkingView.setButton(parkingView.getButton(placeNumber), styleNotAvailableParkingPlace);
            parkingView.setButtonInformations(parkingPlace, placeNumber, vehicleType, licencePlate);
        }
    }
}
