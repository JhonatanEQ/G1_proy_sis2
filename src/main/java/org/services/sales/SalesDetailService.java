/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.services.sales;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.model.SalesDetailModel;
import org.model.config.DatabaseConnection;
import org.services.utils.SalesDetail;

/**
 *
 * @author Encin
 */
public class SalesDetailService {
    private DatabaseConnection gConnDB;
    public SalesDetailService() {
        this.gConnDB = new DatabaseConnection();
    }
    
    public List<SalesDetail> getAllProducts(int idSale) throws SQLException {
        try (Connection conn = gConnDB.getConnection()) {
            return SalesDetailModel.getByIdSale(conn, idSale);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
    
    public boolean save(SalesDetail detail) throws SQLException {
       try (Connection conn = gConnDB.getConnection()) {
           return SalesDetailModel.save(conn, detail);
       } catch (SQLException e) {
           return false;
       }
   }
}