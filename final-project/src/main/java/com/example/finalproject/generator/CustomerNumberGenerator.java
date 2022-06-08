package com.example.finalproject.generator;

import java.util.Random;

public class CustomerNumberGenerator {

    public static Long customerNumberGenerator(int customerNumberSize) {

        String customerNumber = "";

        Random random = new Random();

        for (int i = 0; i < customerNumberSize; i++) {
            int n = random.nextInt(10);
            customerNumber += Integer.toString(n);

        }

        return Long.parseLong(customerNumber);

    }
}

