/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.services.product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.model.ProductModel;
import org.model.config.DatabaseConnection;
import org.services.utils.Product;

/**
 *
 * @author BORIS
 */
public class ProductService {
    private DatabaseConnection gConnDB;
    public ProductService() {
        this.gConnDB = new DatabaseConnection();
    }

    public List<Product> obtenerProductos() throws SQLException {
        try (Connection conn = gConnDB.getConnection()) {
            return ProductModel.getAll(conn);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
    
    
}
