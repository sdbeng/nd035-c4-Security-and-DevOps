package com.example.demo.controllers;

import com.example.demo.Logger.Logger2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/error")
public class ErrorController {
    @Autowired
    private Logger2 logger2;

    @GetMapping("/trigger")
    public String triggerServerError() {
        logger2.logToCsv(null, "Service: Exception", "Triggering an exception", "500");
        throw new RuntimeException("This is a triggered exception");
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        logger2.logToCsv(null, "Service: Exception", e.getMessage(), "500");
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
