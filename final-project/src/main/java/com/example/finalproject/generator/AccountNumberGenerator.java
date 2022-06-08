package com.example.finalproject.generator;

import java.util.Random;

public class AccountNumberGenerator {

    //account number generator
    public static String accountNumberGenerator(int accountNumberSize) {

        String accountNumber="";

        Random random = new Random();

        for(int i=0; i<accountNumberSize; i++)
        {
            int n = random.nextInt(10);


            accountNumber += Integer.toString(n);

        }

        return accountNumber;

    }
}
