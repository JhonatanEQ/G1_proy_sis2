/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.services.utils.Product;

/**
 *
 * @author Encin
 */
public class ProductModel {
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
    
    

    // Getters and Setters
    

    public static List<Product> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM productos";
        List<Product> products = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setCode(rs.getString("codigo"));
                p.setName(rs.getString("nombre"));
                p.setUnitPrice(rs.getDouble("precio_unitario"));
                p.setCategoryId(rs.getInt("categoria_id"));
                p.setCurrentStock(rs.getInt("stock_actual"));
                p.setMinimumStock(rs.getInt("stock_minimo"));
                p.setEntryDate(rs.getString("fecha_entrada"));
                p.setSupplierName(rs.getString("nombre_proveedor"));
                p.setImage(rs.getString("imagen_url"));
                p.setStatus(rs.getBoolean("activo"));
                products.add(p);
            }
        }
        return products;
    }
}
