package org;

import javax.swing.*;
import org.model.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import org.gui.home.Home;

public class Main {
    public static void main(String[] args) {
        // Verificar conexión a la base de datos antes de lanzar la UI
       try {
            DatabaseConnection.printConnectionDetails();
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("Conexión exitosa!");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar: " + e.getMessage());
        }
        
        
        SwingUtilities.invokeLater(() -> {
            Home home = new Home();
            home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            home.setLocationRelativeTo(null); 
            home.setVisible(true);
        });
    }
}