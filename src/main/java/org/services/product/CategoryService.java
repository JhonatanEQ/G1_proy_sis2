/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.services.product;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.model.CategoryModel;
import org.model.config.DatabaseConnection;
import org.services.utils.Category;
/**
 *
 * @author Encin
 */
public class CategoryService {
    private DatabaseConnection gConnDB;
    public CategoryService() {
        this.gConnDB = new DatabaseConnection();
    }
    
    public List<Category> getAllCategories() throws SQLException {
        try (Connection conn = gConnDB.getConnection()) {
            return CategoryModel.getAllCategories(conn);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

}
