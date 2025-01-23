package org.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://dpg-cu97u9i3esus73a0npcg-a.oregon-postgres.render.com/techpointdb";
    private static final String USER = "techpointdb";
    private static final String PASSWORD = "r5ktYUf8VZ0NkNXLsQHofmVsDunbud2x";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
