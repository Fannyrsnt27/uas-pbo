/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fany.movierentapp.movie;

/**
 *
 * @author User
 */
public class Movie {
    private int id;
    private String movieTitle;
    private String movieGenre;

    public Movie(int id, String movieTitle, String movieGenre) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.movieGenre = movieGenre;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }
}