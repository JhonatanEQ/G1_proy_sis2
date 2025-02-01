/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.services.utils;

/**
 *
 * @author Encin
 */
public class Supplier {
    private int gIdSupplier;
    private String gCompanyName;

    public Supplier() {
    }

    public Supplier(int pIdSupplier, String pCompanyName) {
        gIdSupplier = pIdSupplier;
        gCompanyName = pCompanyName;
    }
    
    public int getIdSupplier() {
        return gIdSupplier;
    }

    public void setIdSupplier(int pIdSupplier) {
        gIdSupplier = pIdSupplier;
    }

    public String getCompanyName() {
        return gCompanyName;
    }

    public void setCompanyName(String pCompanyName) {
        gCompanyName = pCompanyName;
    }
    
    @Override
    public String toString() {
        return gCompanyName;
    }
    
    
}
