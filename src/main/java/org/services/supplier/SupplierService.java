/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.services.supplier;

/**
 *
 * @author Encin
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.services.utils.Supplier;
import org.model.config.DatabaseConnection;
import org.model.SupplierModel;

public class SupplierService {
    private DatabaseConnection gConnDB;

    public SupplierService() {
        this.gConnDB = new DatabaseConnection();
    }
    
    public List<Supplier> getAllSuppliers() throws SQLException {
        try (Connection conn = gConnDB.getConnection()) {
            return SupplierModel.getAllSuppliers(conn);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
    
    public int insertOneSupplier(Supplier supplier) throws SQLException {
        try (Connection conn = gConnDB.getConnection()) {
            return SupplierModel.insertOneSupplier(conn, supplier);
        } catch (SQLException e) { 
            e.printStackTrace();
            return 0;
        }
    }

}