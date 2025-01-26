package org;

import javax.swing.*;
import org.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import org.gui.home.Home;

public class Main {
    public static void main(String[] args) {
        // Verificar conexión a la base de datos antes de lanzar la UI
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("✅ Conexión a PostgreSQL exitosa.");
        } catch (SQLException e) {
            System.err.println("❌ Error de conexión a la base de datos.");
            return;
        }
        // Iniciar la interfaz gráfica en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            Home home = new Home();
            home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            home.setLocationRelativeTo(null); 
            home.setVisible(true);
        });
    }
}