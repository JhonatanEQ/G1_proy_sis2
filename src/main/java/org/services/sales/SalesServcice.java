package org.services.sales;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.model.ProductModel;
import org.model.SaleModel;
import org.model.SalesDetailModel;
import org.model.config.DatabaseConnection;
import org.services.utils.Sale;
import org.services.utils.SalesDetail;

public class SalesServcice {
    private DatabaseConnection gConnDB;
    public SalesServcice() {
        this.gConnDB = new DatabaseConnection();
    }
    
    public List<Sale> getAllSales() throws SQLException {
        try (Connection conn = gConnDB.getConnection()) {
            return SaleModel.getAll(conn);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
    
    public boolean save(Sale sale) throws SQLException {
        try (Connection conn = gConnDB.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Save the sale
                if (SaleModel.save(conn, sale)) {
                    // Save each detail and update product stock
                    for (SalesDetail detail : sale.getDetails()) {
                        detail.setIdSale(sale.getIdSale());
                        
                        // Save the sale detail
                        if (!SalesDetailModel.save(conn, detail)) {
                            conn.rollback();
                            return false;
                        }
                        
                        // Update the product stock
                        if (!ProductModel.updateStockAfterSale(conn, detail.getIdProduct(), detail.getQuantity())) {
                            conn.rollback();
                            return false;
                        }
                    }
                    conn.commit();
                    return true;
                }
                conn.rollback();
                return false;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
     }
    
     public List<Sale> getRecentSales(int limit) throws SQLException {
        try (Connection conn = gConnDB.getConnection()) {
            SaleModel saleModel = new SaleModel();
            return saleModel.getRecentSales(conn, limit);
        } catch (SQLException e) {
            throw e;
        }
    }
     
    public List<Sale> getSalesByDate(java.util.Date date) throws SQLException {
        try (Connection conn = gConnDB.getConnection()) {
            return SaleModel.getSalesByDate(conn, date);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
}
