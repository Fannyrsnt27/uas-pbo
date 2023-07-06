/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fany.movierentapp.rent;

import java.util.Date;

/**
 *
 * @author User
 */
public class Rent {

    private int id;
    private Date rentDate;
    private Date returnDate;
    private int movieId;
    private String renter;

    public Rent(int id, Date rentDate, Date returnDate, int movieId, String renter) {
        this.id = id;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.movieId = movieId;
        this.renter = renter;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }
}
