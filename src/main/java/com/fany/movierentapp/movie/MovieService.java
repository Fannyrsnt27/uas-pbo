package com.fany.movierentapp.movie;

import com.fany.movierentapp.connection.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieService {

    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public MovieService() {
        connection = Conn.getConnection();
    }

    public void addMovie(int id, String movieTitle, String movieGenre) {
        try {
            String query = "INSERT INTO movies (id, movie_title, movie_genre) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, movieTitle);
            preparedStatement.setString(3, movieGenre);
            preparedStatement.executeUpdate();

            System.out.println("Movie added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> listMovies() {
        List<Movie> movies = new ArrayList<>();

        try {
            String query = "SELECT * FROM movies";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String movieTitle = resultSet.getString("movie_title");
                String movieGenre = resultSet.getString("movie_genre");

                Movie movie = new Movie(id, movieTitle, movieGenre);
                movies.add(movie);
            }
        } catch (SQLException e) {
        }

        return movies;
    }
    
    public void deleteMovie(int id) {
        try {
            String query = "DELETE FROM movies WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Movie deleted successfully!");
            } else {
                System.out.println("Movie not found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
