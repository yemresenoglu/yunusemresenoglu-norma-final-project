package com.example.finalproject.exception;

public class UserServiceOperationException {

    public static class UserNotFoundException extends BaseException{

        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class UserDefinedException extends BaseException{
        public UserDefinedException(String message) {
            super(message);
        }
    }

    public static class UserRegistrationException extends BaseException{

        public UserRegistrationException(String message) {
            super(message);
        }
    }
}
