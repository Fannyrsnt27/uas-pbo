package com.fany.movierentapp.user;

import com.fany.movierentapp.connection.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginService {
    
    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String sql;

    public LoginService() {
        connection = Conn.getConnection();
    }

    public boolean authenticateUser(String id, String password) {
        try {
            String query = "SELECT * FROM users WHERE id = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); // Jika ada hasil, otentikasi berhasil
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Otentikasi gagal
    }
    
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            String query = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String password = resultSet.getString("password");
                User user = new User(id, password);
                users.add(user);
            }
        } catch (SQLException e) {
        }

        return users;
    }
    
    public void changePassword(String id, String newPassword) {
        try {
            String query = "UPDATE users SET password = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newPassword);
            statement.setString(2, id);
            statement.executeUpdate();
            
            System.out.println("Password changed successfully!");
        } catch (SQLException e) {
        }
    }
    
    public void addUser(String id, String password) {
        try {
            String query = "INSERT INTO users (id, password) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            
            System.out.println("User added successfully!");
        } catch (SQLException e) {
        }
    }
}