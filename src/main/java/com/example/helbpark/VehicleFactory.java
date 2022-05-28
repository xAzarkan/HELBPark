package com.example.helbpark;

public class VehicleFactory {
    //fabrique de véhicules
    //application du pattern factory
    private static VehicleFactory vehicleFactory;

    private VehicleFactory()
    {

    }

    public static VehicleFactory getInstance(){
        //pattern singleton
        if(vehicleFactory == null)
            vehicleFactory = new VehicleFactory();

        return vehicleFactory;
    }

    //fabrique de véhicule
    public Vehicle create(String typeVehicle, String licencePlate){

        if(typeVehicle.equals("moto") || typeVehicle.equals("Motorcycle")){
            return new Motorcycle(licencePlate);
        }
        else if(typeVehicle.equals("voiture") || typeVehicle.equals("Car")) {
            return new Car(licencePlate);
        }
        else if(typeVehicle.equals("camionette") || typeVehicle.equals("Truck")) {
            return new Truck(licencePlate);
        }

        return null;
    }
}


