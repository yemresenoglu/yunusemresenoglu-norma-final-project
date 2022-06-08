package com.example.finalproject.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringConvertToDate {

    public static Date stringToConvertDate(String source) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date expiredDate = dateFormat.parse(source);

        return expiredDate;

    }
}
