/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.services.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BORIS
 */
public class Product {
    private int gId;
    private String gCode;
    private String gName;
    private double gUnitPrice;
    private int gCategoryId;
    private int gCurrentStock;
    private int gMinimumStock;
    private String gEntryDate;
    private String gSupplierName;
    private String gImage;
    private boolean gStatus;

    public Product(String pName, double pUnitPrice, String pImage, boolean pStatus) {
        gName = pName;
        gUnitPrice = pUnitPrice;
        gImage = pImage;
        gStatus = pStatus;
    }


    

    // Empty constructor
    public Product() {}

    // Constructor with parameters
    public Product(int lId, String lCode, String lName, double lUnitPrice, int lCategoryId,
                   int lCurrentStock, int lMinimumStock, String lEntryDate, String lSupplierName, 
                   String lImageUrl, boolean lStatus ) {
        this.gId = lId;
        this.gCode = lCode;
        this.gName = lName;
        this.gUnitPrice = lUnitPrice;
        this.gCategoryId = lCategoryId;
        this.gCurrentStock = lCurrentStock;
        this.gMinimumStock = lMinimumStock;
        this.gEntryDate = lEntryDate;
        this.gSupplierName = lSupplierName;
        this.gImage = lImageUrl;
        this.gStatus = lStatus ;
    }

    // Getters and setters with 'g' prefix for global variables
    public int getId() {
        return gId;
    }

    public void setId(int lId) {
        this.gId = lId;
    }

    public String getCode() {
        return gCode;
    }

    public void setCode(String lCode) {
        this.gCode = lCode;
    }

    public String getName() {
        return gName;
    }

    public void setName(String lName) {
        this.gName = lName;
    }

    public double getUnitPrice() {
        return gUnitPrice;
    }

    public void setUnitPrice(double lUnitPrice) {
        this.gUnitPrice = lUnitPrice;
    }

    public int getCategoryId() {
        return gCategoryId;
    }

    public void setCategoryId(int lCategoryId) {
        this.gCategoryId = lCategoryId;
    }

    public int getCurrentStock() {
        return gCurrentStock;
    }

    public void setCurrentStock(int lCurrentStock) {
        this.gCurrentStock = lCurrentStock;
    }

    public int getMinimumStock() {
        return gMinimumStock;
    }

    public void setMinimumStock(int lMinimumStock) {
        this.gMinimumStock = lMinimumStock;
    }

    public String getEntryDate() {
        return gEntryDate;
    }

    public void setEntryDate(String lEntryDate) {
        this.gEntryDate = lEntryDate;
    }

    public String getSupplierName() {
        return gSupplierName;
    }

    public void setSupplierName(String lSupplierName) {
        this.gSupplierName = lSupplierName;
    }

    public String getImage() {
        return gImage;
    }

    public void setImage(String lImage) {
        this.gImage = lImage;
    }

    public boolean getStatus() {
        return gStatus ;
    }

    public void setStatus(boolean lStatus ) {
        this.gStatus = lStatus ;
    }

    // CRUD methods
    public static List<Product> getAll(Connection lConn) throws Exception {
        String lQuery = "SELECT * FROM productos";
        List<Product> lProducts = new ArrayList<>();

        try (PreparedStatement lStmt = lConn.prepareStatement(lQuery);
             ResultSet lRs = lStmt.executeQuery()) {
            while (lRs.next()) {
                lProducts.add(new Product(
                        lRs.getInt("id"), lRs.getString("codigo"), lRs.getString("nombre"),
                        lRs.getDouble("precio_unitario"), lRs.getInt("categoria_id"),
                        lRs.getInt("stock_actual"), lRs.getInt("stock_minimo"),
                        lRs.getString("fecha_entrada"), lRs.getString("nombre_proveedor"),
                        lRs.getString("imagen_url"), lRs.getBoolean("activo")
                ));
            }
        }
        return lProducts;
    }
}

