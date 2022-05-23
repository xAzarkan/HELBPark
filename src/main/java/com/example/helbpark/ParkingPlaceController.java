package com.example.helbpark;

public class ParkingPlaceController {

    private ParkingPlace parkingPlace;
    private ParkingPlaceView parkingPlaceView;

    public ParkingPlaceController(ParkingPlace parkingPlace, ParkingPlaceView parkingPlaceView)
    {
        this.parkingPlace = parkingPlace;
        this.parkingPlaceView = parkingPlaceView;
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

    public void updateView(ParkingView parkingView)
    {
        parkingPlaceView.updateParkingPlace(parkingPlace, parkingView);
    }
}
