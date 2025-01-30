/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.services.utils;

/**
 *
 * @author Encin
 */
public class Category {
    private int gId;  
    private String gName;

    public Category() {
    }

    public int getgId() {
        return gId;
    }
    
    public void setId(int lId) {
        gId = lId;
    }
    

    public String getgName() {
        return gName;
    }

    public Category(String gName) {
        this.gName = gName;
    }
    
    public void setName(String gName) {
        this.gName = gName;
    }
    @Override
    public String toString() {
        return this.gName;
    }
}
