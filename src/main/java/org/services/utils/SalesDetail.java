package org.services.utils;
/**
 * Clase que representa el detalle de una venta.
 */
public class SalesDetail {
    private int idDetail;
    private int idSale;
    private int idProduct; 
    private int quantity; 
    private double unitPrice; 
    private double subtotal;
    private String productName; // Nuevo campo
    
    // Constructor vacío
    public SalesDetail() {}
    
    // Constructor con parámetros
    public SalesDetail(int idDetail, int idSale, int idProduct, int quantity, double unitPrice, double subtotal, String productName) {
        this.idDetail = idDetail;
        this.idSale = idSale;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
        this.productName = productName;
    }
    
    // Constructor original para mantener compatibilidad
    public SalesDetail(int idDetail, int idSale, int idProduct, int quantity, double unitPrice, double subtotal) {
        this(idDetail, idSale, idProduct, quantity, unitPrice, subtotal, null);
    }
    
    // Getters y Setters existentes
    public int getIdDetail() {
        return idDetail;
    }
    
    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }
    
    public int getIdSale() {
        return idSale;
    }
    
    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }
    
    public int getIdProduct() {
        return idProduct;
    }
    
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public double getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    // Nuevo getter y setter para productName
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    @Override
    public String toString() {
        return "SalesDetail{" +
                "idDetail=" + idDetail +
                ", idSale=" + idSale +
                ", idProduct=" + idProduct +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subtotal=" + subtotal +
                ", productName='" + productName + '\'' +
                '}';
    }
}