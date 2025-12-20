package com.example.demo.util;

public class CoordinateValidator {
    public static boolean isValidLatitude(Double lat) {
        return lat != null && lat >= -90 && lat <= 90;
    }

    public static boolean isValidLongitude(Double lon) {
        return lon != null && lon >= -180 && lon <= 180;
    }

    public static boolean isValidCoordinates(Double lat, Double lon) {
        return isValidLatitude(lat) && isValidLongitude(lon);
    }
}