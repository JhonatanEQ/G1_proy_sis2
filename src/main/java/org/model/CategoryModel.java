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
import org.services.utils.Category;
/**
 *
 * @author Encin
 */
public class CategoryModel {
    public static List<Category> getAllCategories(Connection conn) throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categorias";  
        
        
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));  
                category.setName(rs.getString("nombre"));
                categories.add(category);
            }
        }
        return categories;
    }
}
