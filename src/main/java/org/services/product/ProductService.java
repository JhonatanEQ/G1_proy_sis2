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
    
    public boolean insertOneProduct(Product product)  {
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

    public Product findProductByCode(String codigo) {
        try (Connection conn = gConnDB.getConnection()) {
            return ProductModel.findProductByCode(conn, codigo);
        } catch (SQLException e) {
            // Es mejor lanzar una excepción que retornar null
            // para que la capa superior pueda manejar el error apropiadamente
            throw new RuntimeException("Error al buscar el producto por código: " + e.getMessage(), e);
        }
    }    
}
