/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utils;

/**
 *
 * @author BORIS
 */
public class Product {
    private int gId;
    private String gName;
    private String gEntryDate;
    private double gUnitPrice;
    private String gCategory;
    private int gQuantity;
    private String gImage;
    private String gCompanyName;
    private boolean gStatus;

 
    // Constructor 1
    public Product(String pName, double pUnitPrice, String pImage, boolean pStatus) {
        gName = pName;
        gUnitPrice = pUnitPrice;
        gImage = pImage;
        gStatus = pStatus;
    }

    // Constructor 2
    public Product(int pId, String pName, String pEntryDate, double pUnitPrice, String pCategory, int pQuantity, String pImage, String pCompanyName, boolean pStatus) {
        gId = pId;
        gName = pName;
        gEntryDate = pEntryDate;
        gUnitPrice = pUnitPrice;
        gCategory = pCategory;
        gQuantity = pQuantity;
        gImage = pImage;
        gCompanyName = pCompanyName;
        gStatus = pStatus;
    }

    // Setters
    public void setId(int pId) {
        this.gId = pId;
    }

    public void setName(String pName) {
        this.gName = pName;
    }

    public void setEntryDate(String pEntryDate) {
        this.gEntryDate = pEntryDate;
    }

    public void setUnitPrice(double pUnitPrice) {
        this.gUnitPrice = pUnitPrice;
    }

    public void setCategory(String pCategory) {
        this.gCategory = pCategory;
    }

    public void setQuantity(int pQuantity) {
        this.gQuantity = pQuantity;
    }

    public void setImage(String pImage) {
        this.gImage = pImage;
    }

    public void setCompanyName(String pCompanyName) {
        this.gCompanyName = pCompanyName;
    }

    public void setStatus(boolean pStatus) {
        this.gStatus = pStatus;
    }

    // Getters
    public int getId() {
        return gId;
    }

    public String getName() {
        return gName;
    }

    public String getEntryDate() {
        return gEntryDate;
    }

    public double getUnitPrice() {
        return gUnitPrice;
    }

    public String getCategory() {
        return gCategory;
    }

    public int getQuantity() {
        return gQuantity;
    }

    public String getImage() {
        return gImage;
    }

    public String getCompanyName() {
        return gCompanyName;
    }

    public boolean getStatus() {
        return gStatus;
    }

}
