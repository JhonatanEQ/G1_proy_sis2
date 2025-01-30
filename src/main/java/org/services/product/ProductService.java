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

    public List<Product> getAllProducts() throws SQLException {
        try (Connection conn = gConnDB.getConnection()) {
            return ProductModel.getAll(conn);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
    
    public boolean insertOneProduct(Product product) {
         System.out.println("Código: " + product.getCode());
            System.out.println("Nombre: " + product.getName());
            System.out.println("Precio Unitario: " + product.getUnitPrice());
            System.out.println("Categoría: " + product.getCategoryId());
            System.out.println("Stock Actual: " + product.getCurrentStock());
            System.out.println("Stock Mínimo: " + product.getMinimumStock());
            System.out.println("Fecha Entrada: " + product.getEntryDate());
            System.out.println("Proveedor: " + product.getSupplierName());
            System.out.println("Imagen URL: " + product.getImage());
            System.out.println("Estado: " + product.getStatus());
        try (Connection conn = gConnDB.getConnection()) {
            return ProductModel.insertOneProduct(conn, product);
        } catch (SQLException e) { 
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateProductStockAndDate(Product product) {
        try (Connection conn = gConnDB.getConnection()) {
            return ProductModel.updateProductStockAndDate(conn, product);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        try (Connection conn = gConnDB.getConnection()) {
            return ProductModel.deleteProduct(conn, productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
