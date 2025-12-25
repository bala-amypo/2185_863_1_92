package com.example.demo.util;

import java.time.LocalDateTime;

public class DateValidator {

    public static boolean isNotFuture(LocalDateTime dateTime) {
        if (dateTime == null) {
            return true;
        }
        return !dateTime.isAfter(LocalDateTime.now());
    }
}
