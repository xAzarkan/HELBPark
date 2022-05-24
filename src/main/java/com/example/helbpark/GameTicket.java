package com.example.helbpark;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public abstract class GameTicket {
    private final int DISCOUNT_CODE_LENGTH = 7;
    protected String typeGameTicket;

    protected int discountValue;

    protected ArrayList<String> allDiscountCodes = new ArrayList<>();
    private String discountCode;
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

        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

        StringBuilder stringBuilder = new StringBuilder();

        Random random = new SecureRandom(); //source : https://stackoverflow.com/questions/31213329/generation-of-referral-or-coupon-code

        for (int i = 0; i < DISCOUNT_CODE_LENGTH; i++) {
            char c = chars[random.nextInt(chars.length)];
            stringBuilder.append(c);
        }

        generatedDiscountCode = stringBuilder.toString();
        System.out.println(generatedDiscountCode);

        return generatedDiscountCode;
    }

    public boolean discountCodeAlreadyExists(String code) {
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

}
