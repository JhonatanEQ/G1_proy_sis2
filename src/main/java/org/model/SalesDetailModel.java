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
import org.services.utils.SalesDetail;

/**
 *
 * @author Encin
 */
public class SalesDetailModel {
    
    public static List<SalesDetail> getByIdSale(Connection conn, int idSale) throws SQLException {
    String query = "SELECT dv.*, p.nombre as nombre_producto " +
                  "FROM detalle_venta dv " +
                  "JOIN productos p ON dv.id_producto = p.id " + // Cambiado para usar 'id' en lugar de 'id_producto'
                  "WHERE dv.id_venta = ?";
    List<SalesDetail> details = new ArrayList<>();
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, idSale);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SalesDetail sd = new SalesDetail();
                sd.setIdDetail(rs.getInt("id_detalle"));
                sd.setIdSale(rs.getInt("id_venta"));
                sd.setIdProduct(rs.getInt("id_producto"));
                sd.setQuantity(rs.getInt("cantidad"));
                sd.setUnitPrice(rs.getDouble("precio_unitario"));
                sd.setSubtotal(rs.getDouble("subtotal"));
                sd.setProductName(rs.getString("nombre_producto"));
                details.add(sd);
            }
        }
    }
    return details;
}
    
    public static boolean save(Connection conn, SalesDetail detail) throws SQLException {
        String query = "INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, detail.getIdSale());
            stmt.setInt(2, detail.getIdProduct());
            stmt.setInt(3, detail.getQuantity());
            stmt.setDouble(4, detail.getUnitPrice());
            stmt.setDouble(5, detail.getSubtotal());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
}
