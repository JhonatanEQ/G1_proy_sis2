package org.model.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Properties properties = new Properties();
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try {
            // Cargar el archivo de propiedades
            InputStream input = DatabaseConnection.class.getResourceAsStream("/config/database.properties");
            if (input == null) {
                // Si no encuentra el archivo, usar valores por defecto
                URL = "jdbc:postgresql://dpg-cu97u9i3esus73a0npcg-a.oregon-postgres.render.com/techpointdb";
                USER = "techpointdb";
                PASSWORD = "r5ktYUf8VZ0NkNXLsQHofmVsDunbud2x";
            } else {
                properties.load(input);
                URL = properties.getProperty("hibernate.connection.url");
                USER = properties.getProperty("hibernate.connection.username");
                PASSWORD = properties.getProperty("hibernate.connection.password");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // En caso de error, usar valores por defecto
            URL = "jdbc:postgresql://dpg-cu97u9i3esus73a0npcg-a.oregon-postgres.render.com/techpointdb";
            USER = "techpointdb";
            PASSWORD = "r5ktYUf8VZ0NkNXLsQHofmVsDunbud2x";
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método auxiliar para verificar la configuración
    public static void printConnectionDetails() {
        System.out.println("URL: " + URL);
        System.out.println("Usuario: " + USER);
        // No imprimir la contraseña por seguridad
    }
}