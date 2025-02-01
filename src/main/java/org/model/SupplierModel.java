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
import java.util.ArrayList;
import java.util.List;

import org.services.utils.Supplier;

/**
 *
 * @author Encin
 */
public class SupplierModel {
    public static List<Supplier> getAllSuppliers(Connection conn)  throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM proveedor";
        
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Supplier lSupplier = new Supplier();
                lSupplier.setIdSupplier(rs.getInt("id"));  
                lSupplier.setCompanyName(rs.getString("nombre_empresa"));
                suppliers.add(lSupplier);
            }
        }   
        return suppliers;
    }
    
    public static int insertOneSupplier(Connection conn, Supplier supplier) throws SQLException {
        String query = "INSERT INTO proveedor (nombre_empresa) VALUES (?) RETURNING id";
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, supplier.getCompanyName());
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new SQLException("No se pudo obtener el ID del nuevo proveedor");
            }
        }
    }
}
