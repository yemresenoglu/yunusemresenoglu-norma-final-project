package com.example.finalproject.generator;

import java.util.Random;

public class CardCVVGenerator {

    public static int cvvGenerator() {
        Random random = new Random();

        String cvv = "";

        for (int i = 0; i < 3; i++) {
            int n = random.nextInt(10);
            cvv += Integer.toString(n);
        }

        return Integer.parseInt(cvv);

    }
}
