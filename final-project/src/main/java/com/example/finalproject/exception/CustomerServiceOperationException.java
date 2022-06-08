package com.example.finalproject.exception;

public class CustomerServiceOperationException {

    public static class CustomerNotValidException extends BaseException {

        public CustomerNotValidException(String message) {
            super(message);
        }
    }

    public static class CustomerNotSavedException extends BaseException {
        public CustomerNotSavedException(String message) {
            super(message);
        }
    }

    public static class CustomerNotFoundException extends BaseException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }

    public static class CustomerAlreadyDeletedException extends BaseException {
        public CustomerAlreadyDeletedException(String message) {
            super(message);
        }
    }

    public static class CustomerAccountBalanceMoreThanZeroException extends BaseException {

        public CustomerAccountBalanceMoreThanZeroException(String message) {
            super(message);
        }
    }

    public static class CustomerCreditCardDebitMoreThanZeroException extends BaseException {
        public CustomerCreditCardDebitMoreThanZeroException(String message) {
            super(message);
        }
    }

}
