/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.services.utils.Sale;

/**
 *
 * @author Encin
 */
public class SaleModel {
    
     public static List<Sale> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM venta";
        List<Sale> sales = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(query); 
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Sale s = new Sale();
                s.setIdSale(rs.getInt("id_venta"));
                s.setDate(rs.getTimestamp("fecha_venta"));
                s.setSubtotal(rs.getDouble("subtotal"));
                s.setTax(rs.getDouble("tax"));
                s.setDiscount(rs.getDouble("descuento"));
                s.setDiscountPercentage(rs.getDouble("descuento_porcentual"));
                s.setTotal(rs.getDouble("total"));
                sales.add(s);
            }
        }
        return sales;
    }
    
    public static boolean save(Connection conn, Sale sale) throws SQLException {
        String query = "INSERT INTO venta (fecha_venta, subtotal, tax, descuento, descuento_porcentual, total) " +
                      "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, new Timestamp(sale.getDate().getTime()));
            stmt.setDouble(2, sale.getSubtotal());
            stmt.setDouble(3, sale.getTax());
            stmt.setDouble(4, sale.getDiscount());
            stmt.setDouble(5, sale.getDiscountPercentage());
            stmt.setDouble(6, sale.getTotal());
            
            if (stmt.executeUpdate() > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        sale.setIdSale(generatedKeys.getInt(1));
                        return true;
                    }
                }
            }
            return false;
        }
    }
    
    public static List<Sale> getRecentSales(Connection conn, int limit) throws SQLException {
        String query = "SELECT * FROM venta ORDER BY fecha_venta DESC, id_venta DESC LIMIT ?";
        List<Sale> sales = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sale sale = new Sale();
                    sale.setIdSale(rs.getInt("id_venta"));
                    sale.setDate(rs.getTimestamp("fecha_venta"));
                    sale.setSubtotal(rs.getDouble("subtotal"));
                    sale.setTax(rs.getDouble("tax"));
                    sale.setDiscount(rs.getDouble("descuento"));
                    sale.setDiscountPercentage(rs.getDouble("descuento_porcentual"));
                    sale.setTotal(rs.getDouble("total"));
                    sales.add(sale);
                }
            }
        }
        return sales;
    }
    
    public static List<Sale> getSalesByDate(Connection conn, java.util.Date date) throws SQLException {
        String query = "SELECT * FROM venta WHERE DATE(fecha_venta) = ?";
        List<Sale> sales = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(date.getTime()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sale sale = new Sale();
                    sale.setIdSale(rs.getInt("id_venta"));
                    sale.setDate(rs.getTimestamp("fecha_venta"));
                    sale.setSubtotal(rs.getDouble("subtotal"));
                    sale.setTax(rs.getDouble("tax"));
                    sale.setDiscount(rs.getDouble("descuento"));
                    sale.setDiscountPercentage(rs.getDouble("descuento_porcentual"));
                    sale.setTotal(rs.getDouble("total"));
                    sales.add(sale);
                }
            }
        }
        return sales;
    }
}
