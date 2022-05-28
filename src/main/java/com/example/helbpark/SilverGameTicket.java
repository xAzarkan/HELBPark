package com.example.helbpark;

import java.util.Random;

public class SilverGameTicket extends GameTicket{

    private final String letters = "OXP";
    private final int TWO_SYMBOLS_LENGTH = 2;
    public SilverGameTicket() {

        final int MAX_RANDOM = 10;

        int randomNumber = (int)(Math.random()* MAX_RANDOM); //génère un nombre random

        if(randomNumber%2 == 0) //si le nombre généré est pair
            discountValue = 10; //10% de réduction
        else
            discountValue = 15; //15% de réduction

        Random random = new Random();

        String twoSymbols = "";
        for(int i = 0; i < TWO_SYMBOLS_LENGTH; i++)
        {
            //lettre aléatoire en O,X et P (voir variable "letters")
            twoSymbols += letters.charAt(random.nextInt(letters.length()));
        }

        int indexFirstSymbol = 0; //position du premier symbole dans la chaine de caractère
        int indexSecondSymbol = 1; //position du deuxième symbole dans la chaine de caractère

        if(twoSymbols.charAt(indexFirstSymbol) == twoSymbols.charAt(indexSecondSymbol)){
            //si les deux symboles sont égaux
            discountValue = discountValue * 2; //multiplie la valeur par 2
        }

        typeGameTicket = "Silver ticket";
        game = twoSymbols;
    }


}
