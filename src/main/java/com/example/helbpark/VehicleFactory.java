package com.example.helbpark;

public class VehicleFactory {
    //utilisation du pattern factory
    private static VehicleFactory vehicleFactory;

    private VehicleFactory()
    {

    }

    public static VehicleFactory getInstance(){ //permet d'assurer le pattern Singleton
        if(vehicleFactory == null)
            vehicleFactory = new VehicleFactory();

        return vehicleFactory;
    }

    //fabrique de véhicule
    public Vehicle create(String typeVehicle, String licencePlate){

        if(typeVehicle.equals("moto") || typeVehicle.equals("Motorcycle")){
            System.out.println("moto, plaque : " +  licencePlate);
            return new Motorcycle(licencePlate);
        }
        else if(typeVehicle.equals("voiture") || typeVehicle.equals("Car")) {
            System.out.println("voiture, plaque : " +  licencePlate);
            return new Car(licencePlate);
        }
        else if(typeVehicle.equals("camionette") || typeVehicle.equals("Truck")) {
            System.out.println("camionette, plaque : " +  licencePlate);
            return new Truck(licencePlate);
        }

        return null;
    }
}
