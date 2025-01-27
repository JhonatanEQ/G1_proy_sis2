/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utils;

/**
 *
 * @author HP
 */
public class Product {
    private String gName;
    private String gStatus;
    private double gPrice;
    private String gImagePath;


    public Product(String gName, String gStatus, double gPrice, String gImagePath) {
        this.gName = gName;
        this.gStatus = gStatus;
        this.gPrice = gPrice;
        this.gImagePath = gImagePath;
    }
     public String getgName() {
        return gName;
    }

    public String getgStatus() {
        return gStatus;
    }

    public double getgPrice() {
        return gPrice;
    }

    public String getgImagePath() {
        return gImagePath;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public void setgStatus(String gStatus) {
        this.gStatus = gStatus;
    }

    public void setgPrice(double gPrice) {
        this.gPrice = gPrice;
    }

    public void setgImagePath(String gImagePath) {
        this.gImagePath = gImagePath;
    }
}