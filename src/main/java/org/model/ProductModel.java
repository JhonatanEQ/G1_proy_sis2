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
import org.services.utils.Utils;



/**
 *
 * @author Encinas
 */
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
    public static List<Product> getFilteredProducts(Connection conn, String filtro) throws SQLException {
    List<Product> productos = new ArrayList<>();
    
    // Consulta SQL corregida
    String query = "SELECT * FROM productos " +
                   "WHERE CAST(id AS TEXT) LIKE ? OR " +
                   "nombre LIKE ? OR " +
                   "codigo LIKE ? OR " +
                   "categoria_id IN (SELECT id FROM categorias WHERE nombre LIKE ?)";

    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        // Convertir el filtro a un patrón de búsqueda
        String likePattern = "%" + filtro + "%";
        
        // Asignar los parámetros
        stmt.setString(1, likePattern); // id (convertido a texto)
        stmt.setString(2, likePattern); // nombre
        stmt.setString(3, likePattern); // código
        stmt.setString(4, likePattern); // categoría

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Product producto = new Product();
                producto.setId(rs.getInt("id"));
                producto.setCode(rs.getString("codigo"));
                producto.setName(rs.getString("nombre"));
                producto.setUnitPrice(rs.getDouble("precio_unitario"));
                producto.setCategoryId(rs.getInt("categoria_id"));
                producto.setCurrentStock(rs.getInt("stock_actual"));
                producto.setMinimumStock(rs.getInt("stock_minimo"));
                producto.setEntryDate(rs.getString("fecha_entrada"));
                producto.setSupplierId(rs.getInt("proveedor_id"));

                productos.add(producto);
            }
        }
    }
    return productos;
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
        String query = "UPDATE productos SET stock_actual = ?, fecha_entrada = ? WHERE id = ?";
    
         try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, product.getCurrentStock());
            stmt.setDate(2, (java.sql.Date) Utils.convertToDate(product.getEntryDate()));
            stmt.setInt(3, product.getId());
        
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
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
    
    public static Product findProductByCode(Connection conn, String codigo) throws SQLException {
    String query = "SELECT id, codigo, nombre, precio_unitario, categoria_id, stock_actual, " +
                  "stock_minimo, fecha_entrada, imagen_url, activo, proveedor_id " +
                  "FROM productos WHERE codigo = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, codigo.trim());
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                Product producto = new Product();
                producto.setId(rs.getInt("id"));
                producto.setCode(rs.getString("codigo"));
                producto.setName(rs.getString("nombre"));
                producto.setUnitPrice(rs.getDouble("precio_unitario"));
                producto.setCategoryId(rs.getInt("categoria_id"));
                producto.setCurrentStock(rs.getInt("stock_actual"));
                producto.setMinimumStock(rs.getInt("stock_minimo"));
                producto.setEntryDate(rs.getString("fecha_entrada"));
                producto.setImage(rs.getString("imagen_url"));
                producto.setStatus(rs.getBoolean("activo"));
                producto.setSupplierId(rs.getInt("proveedor_id"));
                
                return producto;
            }
            return null; // Retorna null si no encuentra el producto
        }
    }
}
     public static Product findProductByName(Connection conn, String nombre) throws SQLException {
    String query = "SELECT id, codigo, nombre, precio_unitario, categoria_id, stock_actual, " +
                  "stock_minimo, fecha_entrada, imagen_url, activo, proveedor_id " +
                  "FROM productos WHERE nombre = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, nombre.trim());
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                Product producto = new Product();
                producto.setId(rs.getInt("id"));
                producto.setCode(rs.getString("codigo"));
                producto.setName(rs.getString("nombre"));
                producto.setUnitPrice(rs.getDouble("precio_unitario"));
                producto.setCategoryId(rs.getInt("categoria_id"));
                producto.setCurrentStock(rs.getInt("stock_actual"));
                producto.setMinimumStock(rs.getInt("stock_minimo"));
                producto.setEntryDate(rs.getString("fecha_entrada"));
                producto.setImage(rs.getString("imagen_url"));
                producto.setStatus(rs.getBoolean("activo"));
                producto.setSupplierId(rs.getInt("proveedor_id"));
                
                return producto;
            }
            return null; // Retorna null si no encuentra el producto
        }
    }
    
} 
    
    
    
    
    public static List<Product> findProductsByCategory(Connection conn, int categoriaId) throws SQLException {
    String query = "SELECT id, codigo, nombre, precio_unitario, categoria_id, stock_actual, " +
                   "stock_minimo, fecha_entrada, imagen_url, activo, proveedor_id " +
                   "FROM productos WHERE categoria_id = ?";
    
    List<Product> productos = new ArrayList<>();
    
    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, categoriaId); // Filtrar por ID de categoría
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Product producto = new Product();
                producto.setId(rs.getInt("id"));
                producto.setCode(rs.getString("codigo"));
                producto.setName(rs.getString("nombre"));
                producto.setUnitPrice(rs.getDouble("precio_unitario"));
                producto.setCategoryId(rs.getInt("categoria_id"));
                producto.setCurrentStock(rs.getInt("stock_actual"));
                producto.setMinimumStock(rs.getInt("stock_minimo"));
                producto.setEntryDate(rs.getString("fecha_entrada"));
                producto.setImage(rs.getString("imagen_url"));
                producto.setStatus(rs.getBoolean("activo"));
                producto.setSupplierId(rs.getInt("proveedor_id"));
                
                productos.add(producto);
            }
        }
    }
    
    return productos;
}
}

