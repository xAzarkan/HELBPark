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
            discountValue = 20; //20% de réduction
        else
            discountValue = 40; //40% de réduction

        Random random = new Random();

        String[][] gridOfLetters = new String[ROWS_NUMBER][COLUMNS_NUMBER];

        game = "";

        for(int row = 0; row < ROWS_NUMBER; row++){
            for(int col = 0; col < COLUMNS_NUMBER; col++)
            {
                //génère une lettre random de "PARKHELB" pour chaque cellule
                gridOfLetters[row][col] = String.valueOf(letters.charAt(random.nextInt(letters.length())));
                game += gridOfLetters[row][col];
            }
            game += "\n";
        }

        if(twoSameLettersInSameRowOrColumn(gridOfLetters))
            discountValue = discountValue * 2; //réduction doublée

        typeGameTicket = "Gold ticket";
    }


    public boolean twoSameLettersInSameRowOrColumn(String[][] gridOfLetters)
    { //ATTENTION : solution pas modulaire du tout --> si j'augmente la taille de la grid, ca ne fonctionne plus

        int indexFirstLetter = 0;
        int indexSecondLetter = 1;
        int indexThirdLetter = 2;

        for(int cel = 0; cel < gridOfLetters.length; cel++){

            /* pour les lignes */
            String firstLetterRow = gridOfLetters[cel][indexFirstLetter];
            String secondLetterRow = gridOfLetters[cel][indexSecondLetter];
            String thirdLetterRow = gridOfLetters[cel][indexThirdLetter];

            /* pour les colonnes */
            String firstLetterColumn = gridOfLetters[indexFirstLetter][cel];
            String secondLetterColumn = gridOfLetters[indexSecondLetter][cel];
            String thirdLetterColumn = gridOfLetters[indexThirdLetter][cel];

            if(firstLetterRow.equals(secondLetterRow) || firstLetterRow.equals(thirdLetterRow) || secondLetterRow.equals(thirdLetterRow) ||
                    firstLetterColumn.equals(secondLetterColumn) || firstLetterColumn.equals(thirdLetterColumn) || secondLetterColumn.equals(thirdLetterColumn))
            {
                return true;
            }
        }

        return false;
    }


}
