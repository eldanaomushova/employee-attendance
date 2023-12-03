package com.example.demo4;

import java.sql.*;

public class MusicDAO {
    private Connection conn;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String username = "postgres";
    private String pass = "654321";

    public MusicDAO() {
        try {
            this.conn = DriverManager.getConnection(url);
            System.out.println("Database successfully connected");
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error closing the database connection", e);
        }
    }

    public boolean isEmailInDatabase(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking email in the database", e);
        }

        return false;
    }

    public boolean isPasswordInDatabase(String password) {
        // Note: Storing passwords in plain text is not secure; use proper password hashing.
        String sql = "SELECT COUNT(*) FROM users WHERE password = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking password in the database", e);
        }

        return false;
    }

    public void storeToUserDatabase(String email, String password) {
        // Note: Storing passwords in plain text is not secure; use proper password hashing.
        try (PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO users (email, password) VALUES (?, ?)")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            // Execute the update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error storing data in the database", e);
        }
    }
}
