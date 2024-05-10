package com.example.demo.Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.time.Instant;

public class Logger2 {

    public static final org.apache.logging.log4j.Logger log = LogManager.getLogger();
    private static final Marker CSV_MARKER = MarkerManager.getMarker("CSV");

    public Logger2() {
    }
    public void logToCsv(Number userId,
                         String service,
                         String message,
                         String code)
    {
        String logMessage = String.format("%s, %s, %s, %s, %s", userId != null ? userId : "userTest", Instant.now() ,service, message, code);
        log.debug(CSV_MARKER, logMessage);
    }

    public void logException(Exception e) {
        log.error(CSV_MARKER, e.getMessage(), e);
    }

}
