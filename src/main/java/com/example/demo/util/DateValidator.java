package com.example.demo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateValidator {
    public static boolean isNotFuture(LocalDateTime dt) {
        return dt != null && !dt.isAfter(LocalDateTime.now());
    }

    public static boolean isValidDateRange(LocalDate start, LocalDate end) {
        return start != null && end != null && !start.isAfter(end);
    }
}