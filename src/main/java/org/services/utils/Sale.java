package org.services.utils;
import java.util.Date;
import java.util.List;
import org.services.utils.SalesDetail;

/*
package org.services.utils;

import java.util.Date;
import java.util.List;

/**
 * Clase que representa una venta en el sistema.
 */
public class Sale {
    private int idSale;
    private Date date; 
    private double subtotal; 
    private double tax; 
    private double total; 
    private List<SalesDetail> details; 

    // Constructor vacío
    public Sale() {}

    // Constructor con parámetros
    public Sale(int idSale, Date date, double subtotal, double tax, double total, List<SalesDetail> details) {
        this.idSale = idSale;
        this.date = date;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
        this.details = details;
    }

    // Getters y Setters
    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<SalesDetail> getDetails() {
        return details;
    }

    public void setDetails(List<SalesDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "idSale=" + idSale +
                ", date=" + date +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", total=" + total +
                ", details=" + details +
                '}';
    }
}
