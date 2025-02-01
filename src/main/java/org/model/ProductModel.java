package org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.services.utils.Product;
import org.services.utils.Utils;

public class ProductModel {
    
    

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
                p.setImage(rs.getString("imagen_url"));
                p.setStatus(rs.getBoolean("activo"));
                p.setSupplierId(rs.getInt("proveedor_id"));
                products.add(p);
            }
        }
        return products;
    }
    
    public static boolean insertOneProduct(Connection conn, Product product) throws SQLException {
        String query = "INSERT INTO productos (codigo, nombre, precio_unitario, categoria_id, stock_actual, stock_minimo, fecha_entrada, imagen_url, activo, proveedor_id) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getCode());
            stmt.setString(2, product.getName());
            stmt.setDouble(3, product.getUnitPrice());
            stmt.setInt(4, product.getCategoryId());
            stmt.setInt(5, product.getCurrentStock());
            stmt.setInt(6, product.getMinimumStock());
            stmt.setDate(7, (java.sql.Date) Utils.convertToDate(product.getEntryDate()));
            stmt.setString(8, product.getImage());
            stmt.setBoolean(9, product.getStatus());
            stmt.setInt(10, product.getSupplierId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public static boolean updateProductStockAndDate(Connection conn, Product product) throws SQLException {
        String query = "UPDATE productos SET stock_actual = ?, precio_unitario = ?, fecha_entrada = ? WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            // Actualizar stock
            stmt.setInt(1, product.getCurrentStock());
            
            // Actualizar precio
            stmt.setDouble(2, product.getUnitPrice());
            
            // Convertir y establecer la fecha usando el Utils mejorado
            java.sql.Date sqlDate = Utils.convertToDate(product.getEntryDate());
            stmt.setDate(3, sqlDate);
            
            // ID del producto a actualizar
            stmt.setInt(4, product.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el producto: " + e.getMessage());
            throw e;
        }
    }

    public static boolean deleteProduct(Connection conn, int productId) throws SQLException {
        String query = "DELETE FROM productos WHERE id = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}