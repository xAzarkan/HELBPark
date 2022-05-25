package com.example.helbpark;

import java.security.SecureRandom;
import java.util.Random;

public class GoldGameTicket extends GameTicket{
    private String letters = "PARKHELB";
    private final int ROWS_NUMBER = 3;
    private final int COLUMNS_NUMBER = 3;

    public GoldGameTicket()
    {
        final int MAX_RANDOM = 10;

        int randomNumber = (int)(Math.random()* MAX_RANDOM); //génère un nombre random

        if(randomNumber%2 == 0) //si le nombre généré est pair
        {
            discountValue = 20; //20% de réduction
        }
        else{
            discountValue = 40; //40% de réduction
        }

        Random random = new Random();

        String[][] gridOfLetters = new String[ROWS_NUMBER][COLUMNS_NUMBER];

        game = "";

        for(int row = 0; row < ROWS_NUMBER; row++){
            for(int col = 0; col < COLUMNS_NUMBER; col++)
            {
                gridOfLetters[row][col] = String.valueOf(letters.charAt(random.nextInt(letters.length()))); //génère un nombre random pour chaque cellule
                game += gridOfLetters[row][col];
                System.out.print(gridOfLetters[row][col]);
            }
            System.out.println();
            game += "\n";
        }

        if(twoSameLettersInSameRow(gridOfLetters)){
            discountValue = discountValue * 2; //réduction doublée
            System.out.println("reduction doublee !");
        }

        typeGameTicket = "Gold ticket";
    }

    public boolean twoSameLettersInSameRow(String[][] gridOfLetters)
    {
        int indexFirstLetter = 0;
        int indexSecondLetter = 1;
        int indexThirdLetter = 2;

        /* VERIFICATION DES LIGNES */

        for(int row = 0; row < gridOfLetters.length; row++){

            String firstLetter = gridOfLetters[row][indexFirstLetter];
            String secondLetter = gridOfLetters[row][indexSecondLetter];
            String thirdLetter = gridOfLetters[row][indexThirdLetter];

            if(firstLetter.equals(secondLetter) || firstLetter.equals(thirdLetter) || secondLetter.equals(thirdLetter))
            {
                return true;
            }
        }

        /* VERIFICATION DES COLONNES */

        for(int col = 0; col < gridOfLetters.length; col++){

            String firstLetter = gridOfLetters[indexFirstLetter][col];
            String secondLetter = gridOfLetters[indexSecondLetter][col];
            String thirdLetter = gridOfLetters[indexThirdLetter][col];

            if(firstLetter.equals(secondLetter) || firstLetter.equals(thirdLetter) || secondLetter.equals(thirdLetter))
            {
                return true;
            }
        }
        return false;
    }


}
