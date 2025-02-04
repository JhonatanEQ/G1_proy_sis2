package org.services.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Utils {
    public static Date convertToDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return new Date(System.currentTimeMillis());
        }

        try {
            // First try to parse with datetime format
            SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date parsedDate = fullFormat.parse(dateStr);
            return new Date(parsedDate.getTime());
        } catch (ParseException e1) {
            try {
                // If that fails, try just the date format
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDate = dateFormat.parse(dateStr);
                return new Date(parsedDate.getTime());
            } catch (ParseException e2) {
                try {
                    // Last attempt - try direct SQL date parsing
                    return Date.valueOf(dateStr);
                } catch (IllegalArgumentException e3) {
                    // If all parsing attempts fail, return current date
                    return new Date(System.currentTimeMillis());
                }
            }
        }
    }
}