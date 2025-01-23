package org;

import javax.swing.*;
import org.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Verificar conexión a la base de datos antes de lanzar la UI
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("✅ Conexión a PostgreSQL exitosa.");
        } catch (SQLException e) {
            System.err.println("❌ Error de conexión a la base de datos.");
            e.printStackTrace();
            return;
        }


    }
}