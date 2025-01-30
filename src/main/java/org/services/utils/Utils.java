/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.services.utils;


import java.sql.Date;
/**
 *
 * @author Encin
 */
public class Utils {
    // Static method to convert String to java.sql.Date
    public static Date convertToDate(String dateStr) {
        try {
            // Assuming the date is in "yyyy-MM-dd" format
            return Date.valueOf(dateStr); // Convert String to Date
        } catch (IllegalArgumentException e) {
            // Handle the case where the String is not in the expected format
            e.printStackTrace();
            return null; // Return null if invalid format
        }
    }
}
