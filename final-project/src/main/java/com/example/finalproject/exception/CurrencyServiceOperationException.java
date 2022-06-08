package com.example.finalproject.exception;

public class CurrencyServiceOperationException {


    public static class CurrencyNotValidException extends BaseException{
        public CurrencyNotValidException(String message) {
            super(message);
        }
    }

    public static class CurrencyNotFoundException extends BaseException{
        public CurrencyNotFoundException(String message) {
            super(message);
        }
    }


}
