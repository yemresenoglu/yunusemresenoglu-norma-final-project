package com.example.finalproject.exception;

public class AccountServiceException extends BaseException{

    public AccountServiceException(String message) {
        super(message);
    }

    public static class AccountNotValidException extends BaseException{
        public AccountNotValidException(String message) {
            super(message);
        }
    }

    public static class AccountNotFoundException extends BaseException{
        public AccountNotFoundException(String message) {
            super(message);
        }
    }

    public static class AccountBalanceGraterThanZeroException extends BaseException{
        public AccountBalanceGraterThanZeroException(String message) {
            super(message);
        }
    }
}
