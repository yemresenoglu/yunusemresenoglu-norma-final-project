package com.example.finalproject.exception;

public class CardServiceOperationException extends BaseException {


    public CardServiceOperationException(String message) {
        super(message);
    }

    public static class CardInvalıdException extends BaseException{

        public CardInvalıdException(String message) {
            super(message);
        }
    }
}
