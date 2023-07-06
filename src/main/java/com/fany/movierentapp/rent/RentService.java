package com.fany.movierentapp.rent;

import com.fany.movierentapp.connection.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentService {

    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public RentService() {
        connection = Conn.getConnection();
    }

    public void addNewRent(int id, Date rentDate, Date returnDate, int movieId, String renter) {
        // Check if movieId exists
        if (!isMovieExists(movieId)) {
            System.out.println("Movie with ID " + movieId + " not found.");
            return;
        }

        try {
            String query = "INSERT INTO rent (id, rent_date, return_date, movie_id, renter) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setDate(2, new java.sql.Date(rentDate.getTime()));
            statement.setDate(3, new java.sql.Date(returnDate.getTime()));
            statement.setInt(4, movieId);
            statement.setString(5, renter);
            statement.executeUpdate();
            System.out.println("Rent added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isMovieExists(int movieId) {
        try {
            String query = "SELECT COUNT(*) FROM movies WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Rent> listRent() {
        List<Rent> rentList = new ArrayList<>();
        try {
            String query = "SELECT * FROM rent";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date rentDate = resultSet.getDate("rent_date");
                Date returnDate = resultSet.getDate("return_date");
                int movieId = resultSet.getInt("movie_id");
                String renter = resultSet.getString("renter");
                Rent rent = new Rent(id, rentDate, returnDate, movieId, renter);
                rentList.add(rent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentList;
    }

    public void returnRent(int id) {
        try {
            String query = "DELETE FROM rent WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Rent returned successfully!");
            } else {
                System.out.println("Rent not found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
