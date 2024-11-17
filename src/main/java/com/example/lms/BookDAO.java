package com.example.lms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Author: Kelly Stinson
 * Course: CEN-3024C-14320 Software Development I
 * Date: 11-16-2024
 * Class: BookDAO
 *
 * The BookDAO class provides methods for performing CRUD operations on the "books" database table.
 * It acts as a Data Access Object (DAO) for the Book entity.
 */
public class BookDAO {

    /**
     * Inserts a new book into the database.
     *
     * @param book The Book object to be inserted into the database.
     * @return true if the insertion was successful, false otherwise.
     */
    public static boolean insertBook(Book book) {
        String sql = "INSERT INTO books (Barcode, Title, Author, Genre, Status, DueDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getBarcode());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getGenre());
            stmt.setString(5, book.getStatus());
            stmt.setString(6, book.getDueDate());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing book in the database.
     *
     * @param book The Book object with updated details to be saved in the database.
     * @return true if the update was successful, false otherwise.
     */
    public static boolean updateBook(Book book) {
        String sql = "UPDATE books SET Title = ?, Author = ?, Genre = ?, Status = ?, DueDate = ? WHERE Barcode = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getGenre());
            stmt.setString(4, book.getStatus());
            stmt.setString(5, book.getDueDate());
            stmt.setString(6, book.getBarcode());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a book from the database using its barcode or title.
     *
     * @param identifier The barcode or title of the book to be deleted.
     * @return true if the deletion was successful, false otherwise.
     */
    public static boolean deleteBook(String identifier) {
        String sql = "DELETE FROM books WHERE Barcode = ? or Title = ?";
        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, identifier);
            stmt.setString(2, identifier);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Loads all books from the database into the provided book list.
     *
     * @param books The list to populate with books from the database.
     * @throws SQLException If there is an issue accessing the database.
     */
    public static void loadBooksFromDatabase(List books) {
        // Define the date format for the Due Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Clear any books before loading new books from the database.
        books.clear();

        String sql = "SELECT * FROM books";

        // Load new books from the database
        try (var conn = DatabaseConnector.connect();
             var stmt  = conn.createStatement();
             var rs    = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String barcode = rs.getString("Barcode");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String genre = rs.getString("Genre");
                String dueDate = rs.getDate("DueDate") != null ? rs.getDate("DueDate").toLocalDate().format(formatter) : null;
                String status = (dueDate != null) ? "Checked Out" : "Checked In";
                books.add(new Book(barcode, title, author, genre, status, dueDate));
            }

            System.out.println("Database Loaded Successfully!");
        } catch (SQLException e) {
            System.out.println("Cannot load books from the database: " + e.getMessage());
        }
    }
}

