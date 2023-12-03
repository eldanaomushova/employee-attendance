package com.example.demo4;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MusicDAO {
    private Connection conn;
    String url = "jdbc:postgresql://localhost:5432/database";
    private String username = "postgres";
    private String pass = "21442139";


    public MusicDAO() {
        try {
            Class.forName("org.postgresql.Driver");
//            String url = "jdbc:postgresql://localhost:5432/database";
            this.conn = DriverManager.getConnection(url, username, pass);

        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Or handle the exception appropriately

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    public void closeConnection() {
//        try {
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Error closing the database connection", e);
//        }
//    }
    public boolean isPasswordInDatabase(LogInPage logInPage) {
        String sql = "SELECT * FROM user_info WHERE password = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, logInPage.setPassword());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    System.out.println("Count from the database: " + count);
                    return count > 0;
                }
            } catch (SQLException e) {
                System.err.println("Error processing ResultSet: " + e.getMessage());
                throw new RuntimeException("Error checking password in the database", e);
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
            throw new RuntimeException("Error checking password in the database", e);
        }
        return false;
    }




//    public boolean isPasswordInDatabase(String password) {
//        // Note: Storing passwords in plain text is not secure; use proper password hashing.
//        String sql = "SELECT COUNT(*) FROM users WHERE password = ?";
//
//        try (PreparedStatement statement = conn.prepareStatement(sql)) {
//            statement.setString(1, password);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    int count = resultSet.getInt(1);
//                    return count > 0;
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Error checking password in the database", e);
//        }
//
//        return false;
//    }

//    public void storeToUserDatabase(String email, String password) {
//        // Note: Storing passwords in plain text is not secure; use proper password hashing.
//        try (PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO users (email, password) VALUES (?, ?)")) {
//            preparedStatement.setString(1, email);
//            preparedStatement.setString(2, password);
//
//            // Execute the update
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Error storing data in the database", e);
//        }
//    }
}
