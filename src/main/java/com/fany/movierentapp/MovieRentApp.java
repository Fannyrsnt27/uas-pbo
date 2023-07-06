/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.fany.movierentapp;

import com.fany.movierentapp.movie.Movie;
import com.fany.movierentapp.movie.MovieService;
import com.fany.movierentapp.rent.Rent;
import com.fany.movierentapp.rent.RentService;
import com.fany.movierentapp.user.LoginService;
import com.fany.movierentapp.user.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class MovieRentApp {

    private static String id = null;

    public static void main(String[] args) throws java.text.ParseException {
        LoginService loginService = new LoginService();
        MovieService movieService = new MovieService();
        RentService rentService = new RentService();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Dashboard ===");
            System.out.println("1. Login");
            System.out.println("2. Add User");
            System.out.println("3. Change Password");
            System.out.println("4. Display All Users");
            System.out.println();
            System.out.println("5. Add Movie");
            System.out.println("6. List Movie");
            System.out.println("7. Delete Movie");
            System.out.println();
            System.out.println("8. Add New Rent");
            System.out.println("9. List Active Rent");
            System.out.println("10. Return Rent");
            System.out.println();
            System.out.println("99. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    login(loginService, scanner);
                    break;
                case 2:
                    addUser(loginService, scanner);
                    break;
                case 3:
                    if (id != null) {
                        changePassword(loginService, scanner);
                    } else {
                        System.out.println("Must Login!");
                    }
                    break;
                case 4:
                    if (id != null) {
                        displayAllUsers(loginService);
                    } else {
                        System.out.println("Must Login!");
                    }
                    break;
                case 5:
                    if (id != null) {
                        addMovie(movieService, scanner);
                    } else {
                        System.out.println("Must Login!");
                    }
                    break;
                case 6:
                    if (id != null) {
                        listMovie(movieService, scanner);
                    } else {
                        System.out.println("Must Login!");
                    }
                    break;
                case 7:
                    if (id != null) {
                        deleteMovie(movieService, scanner);
                    } else {
                        System.out.println("Must Login!");
                    }
                    break;
                case 8:
                    if (id != null) {
                        addNewRent(rentService, scanner);
                    } else {
                        System.out.println("Must Login!");
                    }
                    break;
                case 9:
                    if (id != null) {
                        listRent(rentService, scanner);
                    } else {
                        System.out.println("Must Login!");
                    }
                    break;
                case 10:
                    if (id != null) {
                        returnRent(rentService, scanner);
                    } else {
                        System.out.println("Must Login!");
                    }
                    break;
                case 99:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void login(LoginService loginService, Scanner scanner) {
        System.out.println("=== Login ===");
        System.out.print("ID: ");
        id = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        boolean isAuthenticated = loginService.authenticateUser(id, password);
        if (isAuthenticated) {
            System.out.println("Authentication successful!");
        } else {
            System.out.println("Authentication failed!");
        }
    }

    public static void addUser(LoginService loginService, Scanner scanner) {
        System.out.println("=== Add User ===");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        loginService.addUser(id, password);
        System.out.println("User added successfully!");
    }

    public static void changePassword(LoginService loginService, Scanner scanner) {
        System.out.println("=== Change Password ===");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("New Password: ");
        String newPassword = scanner.nextLine();

        loginService.changePassword(id, newPassword);
        System.out.println("Password changed successfully!");
    }

    public static void displayAllUsers(LoginService loginService) {
        System.out.println("=== All Users ===");
        List<User> users = loginService.getAllUsers();

        for (User user : users) {
            System.out.println("ID: " + user.getId());
            System.out.println("Password: " + user.getPassword());
            System.out.println("------------------");
        }
    }

    public static void addMovie(MovieService movieService, Scanner scanner) {
        System.out.println("=== Add Movie ===");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        System.out.print("Movie Title: ");
        String movieTitle = scanner.nextLine();
        System.out.print("Movie Genre: ");
        String movieGenre = scanner.nextLine();

        movieService.addMovie(id, movieTitle, movieGenre);
    }

    public static void listMovie(MovieService movieService, Scanner scanner) {
        List<Movie> movies = movieService.listMovies();

        System.out.println("=== All Movies ===");
        for (Movie movie : movies) {
            System.out.println("ID: " + movie.getId());
            System.out.println("Movie Title: " + movie.getMovieTitle());
            System.out.println("Movie Genre: " + movie.getMovieGenre());
            System.out.println("------------------");
        }
    }

    public static void deleteMovie(MovieService movieService, Scanner scanner) {
        System.out.println("=== Delete Movie ===");
        System.out.print("Enter the ID of the movie to delete: ");
        int movieId = scanner.nextInt();

        movieService.deleteMovie(movieId);
    }

    public static void addNewRent(RentService rentService, Scanner scanner) throws java.text.ParseException {
        // Add New Rent
        System.out.println("=== Add New Rent ===");
        System.out.print("Enter rent ID: ");
        int rentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter rent date (YYYY-MM-DD): ");
        String rentDateStr = scanner.nextLine();
        Date rentDate = parseDate(rentDateStr);
        System.out.print("Enter return date (YYYY-MM-DD): ");
        String returnDateStr = scanner.nextLine();
        Date returnDate = parseDate(returnDateStr);
        System.out.print("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter renter name: ");
        String renter = scanner.nextLine();

        rentService.addNewRent(rentId, rentDate, returnDate, movieId, renter);

    }

    public static List<Rent> listRent(RentService rentService, Scanner scanner) {
// List Rent
        System.out.println("=== List Rent ===");
        List<Rent> rentList = rentService.listRent();
        for (Rent rent : rentList) {
            System.out.println(rent.getId() + " - " + rent.getRenter());
        }
        return rentList;
    }

    public static void returnRent(RentService rentService, Scanner scanner) {
// Return Rent
        System.out.println("=== Return Rent ===");
        System.out.print("Enter the ID of the rent to return: ");
        int rentIdToReturn = scanner.nextInt();
        rentService.returnRent(rentIdToReturn);
    }

    private static Date parseDate(String dateStr) throws java.text.ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateStr);
    }

}
