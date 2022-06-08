package com.example.finalproject.generator;

import java.util.Random;

public class CardNumberGenerator {

    public static String cardNumberGenerator()
    {
        Random random = new Random();

        String cardNumber="4";
        String generatePart="";

        for(int i=0; i<15; i++)
        {
            int n = random.nextInt(10);
            generatePart += Integer.toString(n);
        }

        return cardNumber+generatePart;
    }
}
