package com.example.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class BaseControllerAdvice {


    private void generateErrorLog(Throwable exception) {
        log.error("ErrorMessage:" + exception.getMessage() + ", ErrorDesc:" + exception.getCause());
    }
}
