package com.example.finalproject.validator;

import com.example.finalproject.exception.BaseException;


public interface Validator<T> {

    void validate(T input) throws BaseException;
}
