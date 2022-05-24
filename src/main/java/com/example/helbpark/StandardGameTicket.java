package com.example.helbpark;

import java.security.SecureRandom;
import java.util.Random;

public class StandardGameTicket extends GameTicket{

    public StandardGameTicket()
    {
        final int MAX_RANDOM = 10;

        int randomNumber = (int)(Math.random()* MAX_RANDOM); //génère un nombre random

        if(randomNumber%2 == 0) //si le nombre généré est pair
        {
            discountValue = 5; //5% de réduction
        }
        else{
            discountValue = 10; //10% de réduction
        }

        typeGameTicket = "Standard ticket";
    }

}
