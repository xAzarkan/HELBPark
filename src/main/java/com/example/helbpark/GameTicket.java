package com.example.helbpark;

import java.util.ArrayList;
import java.util.Random;

public abstract class GameTicket {
    private final int DISCOUNT_CODE_LENGTH = 7;
    protected String typeGameTicket;
    protected int discountValue;
    protected ArrayList<String> allDiscountCodes = new ArrayList<>();
    private String discountCode;

    protected String game;

    public GameTicket() {
        String generatedDiscountCode = createDiscountCode(); //génère un code qr pour chaque gameTicket créé

        while(discountCodeAlreadyExists(generatedDiscountCode))
        {
            generatedDiscountCode = createDiscountCode();
        }

        allDiscountCodes.add(generatedDiscountCode);

        this.discountCode = generatedDiscountCode;
    }

    public String getDiscountCode(){
        return this.discountCode;
    }

    public String createDiscountCode(){

        String generatedDiscountCode = "";

        char[] characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

        Random random = new Random();

        for (int i = 0; i < DISCOUNT_CODE_LENGTH; i++) {
            char c = characters[random.nextInt(characters.length)];
            generatedDiscountCode += c;
        }

        return generatedDiscountCode;
    }


    public boolean discountCodeAlreadyExists(String code) {
        //retourne vrai si le code promo existe déjà

        boolean discountCodeExists = false;

        for(String discountCode : allDiscountCodes)
        {
            if(discountCode.equals(code))
                return true;
        }

        return discountCodeExists;
    }

    public String getTypeGameTicket()
    {
        return typeGameTicket;
    }

    public int getDiscountValue(){ return discountValue;}

    public String getGame()
    {
        return game;
    }

}
